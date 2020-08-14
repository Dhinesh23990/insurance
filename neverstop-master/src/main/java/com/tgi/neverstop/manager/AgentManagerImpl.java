package com.tgi.neverstop.manager;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.model.Branch;
import com.tgi.neverstop.model.Agent;
import com.tgi.neverstop.repository.AgentRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class AgentManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(AgentManagerImpl.class);

	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	CommonUtilities commonUtil;

	@Value("${neverstop.static.filepath}")
	private String staticFilePath;

	@Value("${neverstop.images.country.directory}")
	private String countryImgPath;
	
	@Value("${neverstop.static.url}")
	private String fileUrl;
	
	@Value("${neverstop.defaultimages.directory}")
	private String defaultFilePath;

	public Agent saveCountry(@Valid Agent agent) throws NeverStopExcpetion {

		String METHOD_NAME = "saveCountry()";
		logger.info(METHOD_NAME + "start : ");
		try {
			if(agent.getId() ==null ){
				agent.setId(CommonUtilities.generateRandomUUID());
			}
			agent = agentRepository.save(agent);
			
		}catch (DataIntegrityViolationException e) {
			throw new NeverStopExcpetion("agent Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return agent;
	}
	
	
	
	public List<Agent> getAllAgent() {

		String METHOD_NAME = "getAllAgent()";
		logger.info(METHOD_NAME + "start : ");
		List<Agent> agentLst = null;

		try {

			agentLst = agentRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return agentLst;
	}

	public List<Agent> getAgentByBranchId(String branchId) {
		String METHOD_NAME = "geAgentBybranchId()";
		logger.info(METHOD_NAME + "start : ");
		List<Agent> agentList = null;

		try {

			agentList = agentRepository.findByAgentId(branchId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return agentList;
	}

	public @Valid Agent updateCountry(@Valid Agent agent) throws NeverStopExcpetion {
		String METHOD_NAME = "updateCountry()";
		logger.info(METHOD_NAME + "start : ");

		try {
           
			agent = agentRepository.save(agent);

		} catch (DataIntegrityViolationException e) {
			throw new NeverStopExcpetion("agent Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			//re.printStackTrace();
			throw new NeverStopExcpetion(re.getMessage());

		} catch (Throwable e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			throw new NeverStopExcpetion(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return agent;
	}

	public Agent findById(String agentId) {
		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		Agent agent = null;
		try {

			agent = agentRepository.getOne(agentId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return agent;
	}

	public List<Agent> searchbyName(String agentyName) throws NeverStopExcpetion {
		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		List<Agent> agentList = null;

		try {

			agentList = agentRepository.searchbyName(agentyName);
			if (null == agentList || agentList.isEmpty()) {
				throw new NeverStopExcpetion("agenty Not Found");
			}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} 
		logger.info(METHOD_NAME + "END");
		return agentList;
	}
}
