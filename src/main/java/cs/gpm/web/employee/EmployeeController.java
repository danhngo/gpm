package cs.gpm.web.employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import cs.gpm.form.EmployeeForm;
import cs.gpm.form.ImportInfoForm;
import cs.gpm.model.account.EmployeeModel;
import cs.gpm.service.account.EmployeeService;

@Controller("employee")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteEmp(@RequestBody String empId) {
		logger.info("deleteEmp() is executed");
		empId = empId.replace("=", "");
		employeeService.deleteEmployee(empId);
		
		return "success";
	}
		
	
	@RequestMapping(value = "/employee/update", method = RequestMethod.POST)
	public ModelAndView updateEmp(@ModelAttribute EmployeeModel model) {
		logger.info("value() is executed");
		employeeService.updateEmployee(model);
		
		return employeeList();
	}
	
	@RequestMapping(value = "/employee/import", method = RequestMethod.GET)
	public String showImport(Map<String, Object> model) {
		model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("showImport() is executed");
		
		return "employee/import";
	}
	@RequestMapping(value = "/employee/importexcel", method = RequestMethod.POST)
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
				
		employeeService.importEmployee(lstEmployee);
		
		
		List<EmployeeModel> employeeList = employeeService.getEmployeeList();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/list");
		model.addObject("employeeList", employeeList);
		
		return model;
	}
	
	@RequestMapping(value = "/employee/extractdata", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/employee/list", method = RequestMethod.GET)
	public ModelAndView employeeList() {
		//model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("employeeList() is executed");
		
		List<EmployeeModel> employeeList = employeeService.getEmployeeList();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("employee/list");
		EmployeeForm employee = new EmployeeForm();
		model.addObject("employeeForm",employee);
		model.addObject("employeeList", employeeList);
				
		return model;
	}
	
	@RequestMapping(value = "/employee/exportContract", method = RequestMethod.GET)
	public  ResponseEntity<byte[]> exportContract() {
		//model.put("importInfoForm", new ImportInfoForm()); 
		logger.info("exportContract() is executed");
		//   
	
		ResponseEntity<byte[]> response = null;
		try {
			String empId = "JM1";
			EmployeeModel model = employeeService.getEmployeeById(empId);
			if (model == null) return null;
			
			String fileName = "D:/1.Projects/Jmchr.git/gpm/Sources/Hop_Dong_Lao_Dong.doc";
			InputStream fistream = new FileInputStream(fileName);
			
			
			
			HWPFDocument document = new HWPFDocument(fistream);
			
		    document.getRange().replaceText("JMCHR1", model.getName());
		    document.getRange().replaceText("JMCHR2", model.getEmpId());
		    document.getRange().replaceText("JMCHR3", model.getStartdate());
		    
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