package com.tgi.neverstop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import com.tgi.neverstop.manager.AgentManagerImpl;
import com.tgi.neverstop.model.Branch;
import com.tgi.neverstop.model.Agent;
import com.tgi.neverstop.model.ResponseVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/agent")
public class AgentController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(AgentController.class);

	@Autowired
	AgentManagerImpl agentManager;

	@GetMapping("/getAllAgent")
	public ResponseEntity<?> getAllCountry() {

		String METHOD_NAME = "getAllAgent()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<Agent> agentList = agentManager.getAllAgent();
			responseObjectsMap.put("agentList", agentList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select agent.";
		} catch (Throwable e) {
			msg = "Unable to select agent.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}
	
	@PostMapping("/searchByName")
	public ResponseEntity<?> searchbyName(@RequestParam String agentName) {

		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<Agent> agenttList = agentManager.searchbyName(agentName);
			responseObjectsMap.put("agenttList", agenttList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = e.getMessage();
			logger.error(e.getMessage());
		} 
		

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}

	@PostMapping("/getAgentByBranchId")
	public ResponseEntity<?> getAgentByBranchId(
			@RequestParam String branchId) {

		String METHOD_NAME = "getAgentByBranchId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {

			if (branchId != null) {

				List<Agent> agentList = agentManager
						.getAgentByBranchId(branchId);
				responseObjectsMap.put("CountryList", agentList);
			} else {
				throw new Exception("Invalid Request.");
			}
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select Country.";
		} catch (Throwable e) {
			msg = "Unable to select Country.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}

	@PostMapping("/saveAgent")
	public ResponseEntity<?> saveAgent(@Valid @RequestBody Agent agent) {

		String METHOD_NAME = "saveAgent()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			agent = agentManager.saveCountry(agent);
			
			responseObjectsMap.put("AgentVO", agent);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save AgentVO.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}
	
	@PostMapping("/updateAgent")
	public ResponseEntity<?> updateAgent(@Valid @RequestBody Agent agent) {

		String METHOD_NAME = "updateAgent()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			//country = countryManager.updateCountry(country,countryImg);
			agent = agentManager.saveCountry(agent);
			responseObjectsMap.put("AgentVO", agent);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to Update AgentVO-"+re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to Update AgentVO."+e.getMessage();
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}
	}

	@PostMapping("/getAgentById")
	public ResponseEntity<?> getAgentById(@RequestParam String agentId) {

		String METHOD_NAME = "getAgentById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			Agent agent = agentManager.findById(agentId);
			responseObjectsMap.put("Agent", agent);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to save Agent.";
		} catch (Throwable e) {
			msg = "Unable to save Agent.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}
	}

}
