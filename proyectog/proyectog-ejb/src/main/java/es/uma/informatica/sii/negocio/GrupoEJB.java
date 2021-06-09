package es.uma.informatica.sii.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.GruposPorAsignatura;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas.AsignaturasPorMatriculasId;
import es.uma.informatica.sii.entidades.Matricula.MatriculaId;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.GrupoInexistente;
import es.uma.informatica.sii.exceptions.MatriculaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.exceptions.SolicitudDuplicada;

@Stateless
public class GrupoEJB implements GrupoInterface{
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;

	
	@Override
	public List<Asignatura> asignaturasDeGrupo(Grupo grupo){
		
		TypedQuery<GruposPorAsignatura> query = em.createQuery("SELECT g FROM GruposPorAsignatura g", GruposPorAsignatura.class);
		
		List<GruposPorAsignatura> lm = query.getResultList();
		List<Asignatura> asignaturas = new ArrayList<>();
		
		for(GruposPorAsignatura asig : lm) {
			if(asig.getGrupo().equals(grupo)) {
				
				asignaturas.add(asig.getAsignatura());
			}
		}
		
		return asignaturas;
	}

	@Override
	public void asignarGrupos(AlgoritmoSelector algo, Matricula matricula) throws SecretariaException {
		if(algo == null || matricula == null) throw new SecretariaException();
		MatriculaId matId = new MatriculaId(matricula.getCursoAcademico(), matricula.getExpediente().getNumExpediente());
		Matricula mat = em.find(Matricula.class, matId);
		
		if(mat == null) throw new MatriculaInexistente();
		
		Map<Asignatura, Grupo> asignacion = algo.asignarGrupo(mat);
		
		for(Asignatura asignatura : asignacion.keySet()) {
			
			AsignaturasPorMatriculasId asignaturaMatid = new AsignaturasPorMatriculasId(matId, asignatura.getReferencia());
			AsignaturasPorMatriculas asignaturaMat = em.find(AsignaturasPorMatriculas.class, asignaturaMatid);
			
			asignaturaMat.setGrupo(asignacion.get(asignatura));
			
			em.merge(asignaturaMat);
		}
	}
	
//	@Override
//	public void asignarGrupos(Algoritmo selector, Encuesta encuesta) throws SecretariaException {
//		if(encuesta==null){
//			throw new SecretariaException();
//		}
//		
//		Encuesta e = em.find(Encuesta.class, encuesta.getExpediente());
//		
//		if(e == null) {
//			throw new EncuestaInexistente();
//		}
//	
//		selector.aplicarAlgoritmo(0, encuesta);
//		em.merge(encuesta);
//	}
	
	@Override
	public void registrarSolicitudCambioGrupo(SolicitudCambioGrupo solicitud) throws SecretariaException {
		if(solicitud == null) {
			throw new SecretariaException();
		}
		
		Expediente exp = em.find(Expediente.class, solicitud.getExpediente().getNumExpediente());
		
		List<SolicitudCambioGrupo> ls = exp.getSolicitudesPasadas();
		int i = 0;
		boolean enc = false;

		while(i < ls.size() && !enc) {
			if(ls.get(i).equals(solicitud)){
				enc = true;
			}else{
				i++;
			}
		}
		
		if(!enc) {
			em.persist(solicitud);
		}else {
			throw new SolicitudDuplicada(); 
		}
	}
	
	private String ultimoCursoMatriculado(Expediente ex) {
		Integer maxUltimoAnyo = -1;
		for(Matricula m : ex.getMatriculas()) {
			Integer ultimoAnyo = Integer.parseInt(m.getCursoAcademico().substring(3,5));
			if(ultimoAnyo > maxUltimoAnyo) {
				maxUltimoAnyo = ultimoAnyo;
			}
		}
		Integer previoAnyo = maxUltimoAnyo - 1;
		String ultimoCurso = previoAnyo.toString() + "/" + maxUltimoAnyo.toString();
		
		return ultimoCurso;
	}
	
	
	@Override
	public void reasignarGrupo(Expediente expediente, Grupo grupo) throws SecretariaException {
		
		if(expediente == null || grupo == null) {
			throw new SecretariaException();
		}
		Expediente exp = em.find(Expediente.class, expediente.getNumExpediente());
		
		if(exp == null) {
			throw new ExpedienteInexistente();
		}
		
		Grupo g = em.find(Grupo.class, grupo.getId());
		
		if(g == null) {
			throw new GrupoInexistente();
		}
		
		String ultCurso = ultimoCursoMatriculado(exp);
		List<Matricula> lm = exp.getMatriculas();
		Matricula mat = new Matricula();
		for(Matricula m : lm){
			if(m.getCursoAcademico().equals(ultCurso)){
				mat = m;
			}
		}
		//int i = exp.getMatriculas().size()-1;
		//Matricula m = exp.getMatriculas().get(i);
		List<AsignaturasPorMatriculas> apm = mat.getAsignaturasPorMatriculas();

		for(AsignaturasPorMatriculas a : apm){
	
			if(a.getGrupo().getCurso().equals(g.getCurso())){
				a.setGrupo(g);
				em.merge(a);
			}	
		}
	}

	@Override
	public Integer plazasTotales(Grupo grupo) throws SecretariaException {
		
		if(grupo == null) {
			throw new SecretariaException();
		}
	
		Grupo g = em.find(Grupo.class, grupo.getId());
	
		if(g == null) {
			throw new GrupoInexistente();
		}
		
		int plazasTotales = g.getPlazas();
		List<Grupo> lg = g.getRelacionados();
		
		if(!lg.isEmpty()){
			
			for(Grupo gr: lg){
				plazasTotales+=gr.getPlazas();
			}
		}

		return plazasTotales;
	}

	@Override
	public void actualizarGrupo(Grupo grupo) throws SecretariaException {
		if(grupo == null) {
			//Grupo no existe
			throw new GrupoInexistente();
		}
		
		Grupo g = em.find(Grupo.class, grupo.getId());
		
		if(g == null) {
			//Grupo no existe en la BBDD
			throw new GrupoInexistente();
		}

		em.merge(grupo);	
	}
	
	@Override
	public List<Grupo> consultarGrupos() throws SecretariaException {
		List<Grupo> lg = em.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
		return lg;
	}
	
	@Override
	public List<List<String>> consultarGrupos(Integer expediente, List<Integer> curso) throws SecretariaException {
		/*
		if(expediente == null) {
			//Expediente no existe
			throw new ExpedienteInexistente();
		}
		
		Expediente e = em.find(Expediente.class, expediente.getNumExpediente());
		*/
		List<List<String>> res = new ArrayList<>();
		List<String> aux= new ArrayList<>();
		List<Grupo> lg = em.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();

		for(int i=0;i<curso.size();i++){
			for(int j=0;j<lg.size();j++){
				if(lg.get(j).getTitulacion().equals(Integer.parseInt(expediente.toString().substring(0,4))) && curso.get(i).equals(lg.get(j).getCurso())){
					aux.add(lg.get(j).getLetra());
				}	
			}
			res.add(aux);
		}
		
		return res;
	}
	
	@Override
	public List<Grupo> consultarGrupos(Expediente expediente, int curso, boolean ingles) throws SecretariaException {
	if(expediente == null) {
				//Expediente no existe
				throw new ExpedienteInexistente();
	}

	Expediente e = em.find(Expediente.class, expediente.getNumExpediente());
		
	List<Grupo> res = new ArrayList<>();
	List<Grupo> lg = em.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
	for(int i = 0; i < lg.size(); i++) {
		if(lg.get(i).getTitulacion().toString() ==  e.getNumExpediente().toString().substring(0,4) && curso == lg.get(i).getCurso() && ingles){
			res.add(lg.get(i));
		}
	}
		
	return res;	
	}

	@Override
	public List<Grupo> consultarGrupos(Expediente expediente, int curso, boolean ingles, String tarde) throws SecretariaException {
	
		if(expediente == null) {
				//Expediente no existe
				throw new ExpedienteInexistente();
		}

		Expediente e = em.find(Expediente.class, expediente.getNumExpediente());
			
		List<Grupo> res = new ArrayList<>();
		List<Grupo> lg = em.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
		for(int i = 0; i < lg.size(); i++) {
			if(lg.get(i).getTitulacion().toString() ==  e.getNumExpediente().toString().substring(0,4) && curso == lg.get(i).getCurso() && tarde == "Tarde"){
				res.add(lg.get(i));
			}
		}
		return res;
	}
}
