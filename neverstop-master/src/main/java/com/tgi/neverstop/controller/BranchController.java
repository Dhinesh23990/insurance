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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.manager.BranchManagerImpl;
import com.tgi.neverstop.model.Branch;
import com.tgi.neverstop.model.ResponseVO;
import com.tgi.neverstop.model.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/branch")
public class BranchController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(BranchController.class);

	@Autowired
	BranchManagerImpl branchManager;

	@GetMapping("/getAllBranch")
	public ResponseEntity<?> getAllUsers() {

		String METHOD_NAME = "getAllBranch()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<Branch> branchList = branchManager.getAllContinent();
			responseObjectsMap.put("branchList", branchList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to select branch.";
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
	public ResponseEntity<?> searchbyName(@RequestParam String branchName) {

		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<Branch> branchList = branchManager.searchbyName(branchName);
			responseObjectsMap.put("branchList", branchList);
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

	@PostMapping("/saveContinent")
	public ResponseEntity<?> saveContinent(@Valid @RequestBody Branch branch) {

		String METHOD_NAME = "saveBranch()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			branch = branchManager.saveBranch(branch);
			responseObjectsMap.put("Branch", branch);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save continent.";
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
	

	
	@PostMapping("/updateBranch")
	public ResponseEntity<?> updateBranch(@Valid @RequestBody Branch branch) {

		String METHOD_NAME = "updateBranch()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			branch = branchManager.updateBrancht(branch);
			responseObjectsMap.put("BranchVO", branch);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save continent.";
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

	@PostMapping("/getBranchById")
	public ResponseEntity<?> getBranchById(@RequestParam String id) {

		String METHOD_NAME = "getBranchById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			Branch branch = branchManager.findById(id);
			responseObjectsMap.put("BranchVO", branch);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select branch.";
		} catch (Throwable e) {
			msg = "Unable to select branch.";
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
