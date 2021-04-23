package es.uma.informatica.sii.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import es.uma.informatica.sii.entidades.Asignatura;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas;
import es.uma.informatica.sii.entidades.Expediente;
import es.uma.informatica.sii.entidades.Grupo;
import es.uma.informatica.sii.entidades.Matricula;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo;
import es.uma.informatica.sii.entidades.AsignaturasPorMatriculas.AsignaturasPorMatriculasId;
import es.uma.informatica.sii.entidades.Matricula.MatriculaId;
import es.uma.informatica.sii.entidades.SolicitudCambioGrupo.SolicitudCambioGrupoID;
import es.uma.informatica.sii.exceptions.EncuestaInexistente;
import es.uma.informatica.sii.exceptions.ExpedienteInexistente;
import es.uma.informatica.sii.exceptions.GrupoInexistente;
import es.uma.informatica.sii.exceptions.MatriculaInexistente;
import es.uma.informatica.sii.exceptions.SecretariaException;
import es.uma.informatica.sii.exceptions.SolicitudCambioGrupoInexistente;
import es.uma.informatica.sii.exceptions.SolicitudDuplicada;

@Stateless
public class GrupoEJB implements GrupoInterface{
	
	private static final String PERSISTENCE_UNIT = "proyectog-jpa";
	
	@PersistenceUnit(unitName = PERSISTENCE_UNIT)
	private EntityManagerFactory emf;
	@PersistenceContext(name = PERSISTENCE_UNIT)
	private EntityManager em;


	@Override
	public void asignarGrupos(AlgoritmoSelector algo, Matricula matricula) throws SecretariaException {
		if(algo == null || matricula == null) throw new SecretariaException();
		Matricula mat = em.find(Matricula.class, matricula);
		if(mat == null) throw new MatriculaInexistente();
		
		Map<Asignatura, Grupo> asignacion = algo.asignarGrupo(mat);
		
		MatriculaId matId = new MatriculaId(mat.getCursoAcademico(), mat.getExpediente().getNumExpediente());
		for(Asignatura asignatura : asignacion.keySet()) {
			
			AsignaturasPorMatriculasId asignaturaMatid = new AsignaturasPorMatriculasId(matId, asignatura.getCodigo());
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
	
			if(a.getGrupo().getCurso().equals(g.getCurso()) && a.getAsignatura().getPlazas() < g.getPlazas()){
				
				g.setPlazas(g.getPlazas()-1); 
				a.getGrupo().setPlazas(a.getGrupo().getPlazas()+1);
				a.setGrupo(g);
			}	
		}
		em.merge(exp);
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
}
