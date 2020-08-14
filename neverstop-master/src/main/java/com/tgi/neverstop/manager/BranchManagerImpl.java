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
import com.tgi.neverstop.repository.BranchRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class BranchManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(BranchManagerImpl.class);

	@Autowired
	BranchRepository branchRepository;
	

	@Autowired
	CommonUtilities commonUtil;

	
	@Value("${neverstop.static.filepath}")
	private String staticFilePath;

	@Value("${neverstop.images.continent.directory}")
	private String continentImgPath;
	
	@Value("${neverstop.static.url}")
	private String fileUrl;
	
	@Value("${neverstop.defaultimages.directory}")
	private String defaultFilePath;

	public Branch saveBranch(@Valid Branch branch) throws NeverStopExcpetion {

		String METHOD_NAME = "saveBranch()";
		logger.info(METHOD_NAME + "start : ");
		try {
			if(branch.getId() ==null ){
				branch.setId(CommonUtilities.generateRandomUUID());
			}
			branch = branchRepository.save(branch);
			
		}catch (DataIntegrityViolationException e) {
			throw new NeverStopExcpetion("branch Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return branch;
	}
	
	public Branch updateBrancht(@Valid Branch branch) throws NeverStopExcpetion {

		String METHOD_NAME = "saveBrancht()";
		logger.info(METHOD_NAME + "start : ");
		try {
			if(branch.getId() ==null ){
				branch.setId(CommonUtilities.generateRandomUUID());
			}
			//continent.setContinentImg(fileUrl+defaultFilePath);
			branch = branchRepository.save(branch);
			
		}catch (DataIntegrityViolationException e) {
			throw new NeverStopExcpetion("branch Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return branch;
	}
	

	public List<Branch> getAllContinent() {

		String METHOD_NAME = "getAllContinent()";
		logger.info(METHOD_NAME + "start : ");
		List<Branch> branchList = null;

		try {

			branchList = branchRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return branchList;
	}

	public Branch findById(String branchId) {
		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		Branch branch = null;
		try {

			branch = branchRepository.getOne(branchId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return branch;
	}

	public List<Branch> searchbyName(String branchtName) throws NeverStopExcpetion 
	{
		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		List<Branch> branchLst = null;

		try {

			branchLst = branchRepository.searchbyName(branchtName);
			if (null == branchLst || branchLst.isEmpty()) {
				throw new NeverStopExcpetion("branch Not Found");
			}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} 
		logger.info(METHOD_NAME + "END");
		return branchLst;
	}
}
