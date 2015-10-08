package cs.gpm.web.document;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cs.gpm.ExcelUtil;
import cs.gpm.form.DocumentForm;
import cs.gpm.form.ImportInfoForm;
import cs.gpm.model.document.DocumentModel;
import cs.gpm.service.document.DocumentService;

@Controller
public class DocumentController {

	private final Logger logger = LoggerFactory.getLogger(DocumentController.class);
	private final DocumentService documentService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");
		
		return "index";
	}

	@Autowired
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}
	
	@RequestMapping(value = "/document/list", method = RequestMethod.GET)
	public ModelAndView getDocumentList() {
		//model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("getDocumentList() is executed");
		
		List<DocumentModel> documentList = documentService.getDocumentList();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("document/list");
		DocumentForm document = new DocumentForm();
		
		model.addObject("documentForm",document);
		model.addObject("documentList", documentList);
				
		return model;
	}
	
	@RequestMapping(value = "/document/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDocument(@RequestBody String id) {
		logger.info("deleteEmp() is executed");
		id = id.replace("=", "");
		documentService.deleteDocument(id);
		
		return "success";
	}
		
	
	@RequestMapping(value = "/document/update", method = RequestMethod.POST)
	public ModelAndView updateEmp(@ModelAttribute DocumentModel model) {
		logger.info("value() is executed");
		documentService.updateDocument(model);
		
		return getDocumentList();
	}
	
	@RequestMapping(value = "/document/import", method = RequestMethod.GET)
	public String showImport(Map<String, Object> model) {
		model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("showImport() is executed");
		
		return "employee/import";
	}
	
	/*@RequestMapping(value = "/employee/importexcel", method = RequestMethod.POST)
	public ModelAndView importEmployee(@RequestParam("file") MultipartFile file) {

		logger.info("importexcel() is executed");
		//List<EmployeeModel> lstEmployee = ExcelUtil.readEmployeeProfile(filePath);
		String[] fields = new String[] {"Id","Name","Startdate"};
		List<Object[]> lstModel = ExcelUtil.readExcelFile(file, 0, fields);
		
		List<EmployeeModel> lstEmployee = new ArrayList<EmployeeModel>();
		for (Object[] objList : lstModel) {
			EmployeeModel model = new EmployeeModel();
			model.setEmpId((String)objList[0]);
			model.setName((String)objList[1]);
			model.setStartdate((String)objList[2]);
			
			lstEmployee.add(model);
		}
				
		documentService.importEmployee(lstEmployee);
		
		
		List<EmployeeModel> employeeList = documentService.getEmployeeList();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/list");
		model.addObject("employeeList", employeeList);
		
		return model;
	}*/
	
	@RequestMapping(value = "/document/extractdata", method = RequestMethod.POST)
	public ModelAndView extractData(@RequestParam("file") MultipartFile file) {

		logger.info("extractdata() is executed");
		//List<EmployeeModel> lstEmployee = ExcelUtil.readEmployeeProfile(filePath);
		
		try {
			ExcelUtil.extractData(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/import");
		
		return model;
	}
	
	
	
	@RequestMapping(value = "/document/exportContract", method = RequestMethod.GET)
	public  ResponseEntity<byte[]> exportContract() {
		//model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("exportContract() is executed");
		//   
	
		ResponseEntity<byte[]> response = null;
		try {
			String empId = "JM1";
			DocumentModel model = documentService.getDocument(empId);
			if (model == null) return null;
			
			String fileName = "D:/1.Projects/Jmchr.git/gpm/Sources/Hop_Dong_Lao_Dong.doc";
			InputStream fistream = new FileInputStream(fileName);
			
			
			
			HWPFDocument document = new HWPFDocument(fistream);
			
		    document.getRange().replaceText("JMCHR1", model.getName());
		    
		    //String newFileName = "/home/danhngo/Projects/Jmchr/gpm.git/Sources/Hop_Dong_Lao_Dong2.doc";
		    String newFileName = "D:/1.Projects/Jmchr.git/gpm/Sources/Hop_Dong_Lao_Dong2.doc";
		    OutputStream writer = new FileOutputStream(newFileName);
		    document.write(writer);
		    
		    InputStream newFile = new FileInputStream(newFileName);
		    
		    byte[] contents = IOUtils.toByteArray(newFile);
		    
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.parseMediaType("application/msword"));
		    String filename = "Hop_Dong_Lao_Dong2.doc";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    
		    response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		    
		    //FileCopyUtils.copy(fistream, response.getOutputStream());
		    		    
		 /*   org.apache.commons.io.IOUtils.copy(newFile, response.getOutputStream());
		 	    		    
		    //response.setContentType("application/msword");
		    response.setContentType("application/force-download");
		    response.setHeader("Content-Disposition", "attachment; filename=Hop_Dong_Lao_Dong.doc"); 
		     
		    response.flushBuffer();*/
		    
		    fistream.close();
		    newFile.close();
		    
		    /*response.getOutputStream().flush();
		    response.getOutputStream().close();*/
		    		    						
			//System.out.println(document.getText());
			//System.out.println(document.getDocumentText());
			
		} catch (Exception e) {
			logger.info("Error", e.getStackTrace());
		}
		
		return response;
		
	}
	
	

	

	/*@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		model.addObject("title", employeeService.getTitle(name));
		model.addObject("msg", employeeService.getDesc());
		
		return model;

	}*/
	
	

}