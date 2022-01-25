package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.ApplyLeaveController;
import com.example.demo.controller.AttendanceController;
import com.example.demo.controller.AuthController;
import com.example.demo.controller.LeaveController;
import com.example.demo.controller.WorkingHoursController;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.ApplyLeave;
import com.example.demo.model.Attendance;
import com.example.demo.model.ERole;
import com.example.demo.model.LeaveDefaults;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.WorkingHours;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class ServicesTest {
	
	@Autowired
	private AddService addse;
	
	@Autowired
	private GetEmployeeService getserv;
	
	@Autowired
	private ApplyLeaveResponseService aplserv;
	
	@Autowired 
	private ApplyLeaveService apply;
	
	@Mock
	RoleRepository roleRepository;
	
	@Autowired
	AuthController authController;
	
	@InjectMocks
	AuthController authControlle;
	
	@Autowired
	private AttendanceController att;
	
	@Autowired
	private LeaveController lev;
	
	@Autowired
	private WorkingHoursController workhr;
	
	@Autowired
	private ApplyLeaveController appl;
	
	@Mock
	private GetEmployeeService getseq;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	MongoTemplate mongoTemplate;
	
	@Test
	public void getEmployeeByIdTest() throws EmployeeNotFoundException {
		assertTrue(att.getemployeeByID("CS40") instanceof Attendance);
	}
	
	
	@Test
	public void addEmployeeASTest() throws Exception {
		
		long num = 917630487;
		int n = 2;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"AnjaliSweets");
		String rs = addse.addEmployee(atten, "AS");
		assertEquals( rs, "Employee Added" );
	}
	
	@Test
	public void addEmployeeBSTest() throws Exception {
		
		long num = 917630487;
		int n = 5;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"BharathiSweets");
		String rs = addse.addEmployee(atten, "BS");
		assertEquals( rs, "Employee Added" );
	}
	
	@Test
	public void addEmployeeTest() throws Exception {
		
		long num = 917630487;
		int n = 8;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"ChenthurSweets");
		String rs = addse.addEmployee(atten, "CS");
		assertEquals( rs, "Employee Added" );
	}
	
	@Test
	public void addEmployeeCSTest() throws Exception {
		
		long num = 917630487;
		int n = 2;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"ChenthurSweets");
		String rs = addse.addEmployee(atten, "CS");
		assertEquals( rs, "Employee Added" );
	}
	
	@Test
	public void getByEmployeeIdTest() throws EmployeeNotFoundException {
		assertTrue(getserv.getByEmployeeId("BS13") instanceof Attendance);	
	}
	
	@Test
	public void getByEmployeeIdTestNull() {
		try {
            getserv.getByEmployeeId("BS66");
            fail("Should throw an exception");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(EmployeeNotFoundException.class)
                    .hasMessage("Employee not found");
        }
		
	}
	
	@Test
	public void CreateEmployeeASTest() {
		long num = 917630487;
		int n = 2;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vEasjd",num,dob,n,"AnjaliSweets");
		assertTrue(att.CreateEmployee(atten) instanceof Attendance);
	}
	
	@Test
	public void CreateEmployeeBSTest() {
		long num = 917630487;
		int n = 5;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasEjd",num,dob,n,"BharathiSweets");
		assertTrue(att.CreateEmployee(atten) instanceof Attendance);
	}
	
	@Test
	public void CreateEmployeeCSTest() {
		long num = 917630487;
		int n = 8;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjEd",num,dob,n,"ChenthurSweets");
		assertTrue(att.CreateEmployee(atten) instanceof Attendance);
	}
	
	@Test
	public void getEmployeesTest() throws EmployeeNotFoundException {
		assertTrue(att.getEmployees() instanceof List<?>);
	}
	
	@Test
	public void updateEmployeeTest() throws EmployeeNotFoundException {
		long num = 917630487;
		int n = 2;
		LocalDate dob = LocalDate.now();
		Attendance atten = new Attendance("Vijayaraghavan","D","vasjd",num,dob,n,"AnjaliSweets");
		assertTrue(att.updateEmployee("AS2", atten) instanceof ResponseEntity<?>);
	}
	
	@Test
	public void deletingEmployeeTest() throws EmployeeNotFoundException {
		assertTrue(att.deletingEmployee("BS14") instanceof ResponseEntity<?>);
	}
	
	@Test
	public void getTeamsCountTest() {
		assertTrue(att.getTeamsCount("AS") instanceof String);
	}
	
	@Test
	public void getTeamsCountCSTest() {
		assertTrue(att.getTeamsCount("CS") instanceof String);
	}
	
	@Test
	public void getEmpOfPerTeamASTest() throws EmployeeNotFoundException {
		assertTrue(att.getEmpOfPerTeam("AS") instanceof List<?>);
	}
	
	@Test
	public void getEmpOfPerTeamBSTest() throws EmployeeNotFoundException {
		assertTrue(att.getEmpOfPerTeam("BS") instanceof List<?>);
	}
	
	@Test
	public void getEmpOfPerTeamCSTest() throws EmployeeNotFoundException {
		assertTrue(att.getEmpOfPerTeam("CS") instanceof List<?>);
	}
	
	@Test
	public void getTeamCountASTest() {
		assertTrue(Integer.toString(addse.getTeamCount("AS")) instanceof String);
	}
	
	@Test
	public void getTeamsCountBSTest() {
		assertTrue(Integer.toString(addse.getTeamCount("BS")) instanceof String);
	}
	
	@Test
	public void getTeamCountCSTest() {
		assertTrue(Integer.toString(addse.getTeamCount("CS")) instanceof String);
	}
	
	@Test
	public void leaveTakenTest() throws EmployeeNotFoundException {
		assertTrue(lev.leaveTaken("BS14") instanceof String);
	}
	
	@Test
	public void employeeLeaveReportTest() throws EmployeeNotFoundException {
		assertTrue(lev.employeeLeaveReport("BS14") instanceof String);
	}
	
	@Test
	public void remainingLeaveTest() throws EmployeeNotFoundException {
		assertTrue(lev.remainingLeave("BS14") instanceof String);
	}
	
	@Test
	public void createWorkHoursTest() throws EmployeeNotFoundException {
		assertTrue(workhr.createWorkHours("BS14") instanceof WorkingHours);
	}
	
	@Test
	public void addWorkHoursTest() {
		WorkingHours work = new WorkingHours("BS13",null,null,null, null);
		assertTrue(addse.addWorkHrs(work) instanceof String);
	}
	
	@Test
	public void updateEmployeeWorkHoursTest() throws EmployeeNotFoundException {
		assertTrue(workhr.updateEmployeeWorkHours("BS14") instanceof ResponseEntity<?>);
	}
	
	@Test
	public void instatiatingLeaveTest() throws EmployeeNotFoundException{
		
		ApplyLeave app = new ApplyLeave("BS14", null, "ML");
		assertTrue(appl.instatiatingLeave(app) instanceof String);
	}
	
	@Test
	public void getAppliedLeavPerTeamASTest() throws EmployeeNotFoundException {
		assertTrue(appl.getAppliedLeavPerTeam("AS") instanceof List<?>);
	}
	
	@Test
	public void getAppliedLeavPerTeamBSTest() throws EmployeeNotFoundException {
		assertTrue(appl.getAppliedLeavPerTeam("BS") instanceof List<?>);
	}
	
	@Test
	public void getAppliedLeavPerTeamCSTest() throws EmployeeNotFoundException {
		assertTrue(appl.getAppliedLeavPerTeam("CS") instanceof List<?>);
	}
	
	@Test
	public void approvingLeaveTest() throws EmployeeNotFoundException {
		assertTrue(appl.approvingLeave("BS14") instanceof String);
	}
	
	@Test
	public void addAppliedLeaveTest() throws EmployeeNotFoundException {
		ApplyLeave app = new ApplyLeave("BS14", null, "ML");
		assertTrue(aplserv.addAppliedLeave(app) instanceof String);
	}
	
	@Test
	public void signUpTestSameUserName() throws IOException {
		SignupRequest user = new SignupRequest();
		user.setUsername("manager_as");
		User usr = new User(user.getUsername(), user.getEmail(), user.getPassword());
		Mockito.when(userRepository.save(usr)).thenReturn(null);
		assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);
	}
	
	@Test
	public void signUpTestSameEmailId() throws IOException{
		SignupRequest user = new SignupRequest();
		user.setEmail("manager_as@gmail.com");
		User usr = new User(user.getUsername(), user.getEmail(), user.getPassword());
		Mockito.when(userRepository.save(usr)).thenReturn(null);
		assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);
	}
	
	@Test
	public void signUpTestNull() throws IOException{
		SignupRequest user = new SignupRequest();
		user.setUsername("Vasr");
		user.setEmail("adascs@gmail.com");
		user.setPassword("123456");
		User ser =new User();
		ser.setUsername("vijay");
		Set<Role> roles = new HashSet<>();
		Role rol = new Role();
		roles.add(rol);
		Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(null);
		Mockito.when(userRepository.save(ser)).thenReturn(ser);
		assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);	
	}
	
	@Test
	public void signUpTestAdmin() {
		SignupRequest user = new SignupRequest();
		user.setUsername("VRasdasc");
		user.setEmail("vrasxa@gmail.com");
		user.setPassword("123456");
		User ser = new User("Vjr","vg@gmail.com","12439");
		Set<String> rr = new HashSet<>();
		rr.add("admin");
		user.setRole(rr);
		Set<Role> roles = new HashSet<>();
		Role rol = new Role();
		rol.setId("61ec5ce4cd7482b824b2164c");
		rol.setName(ERole.ROLE_ADMIN);
		Optional<Role> ro = null;
		Mockito.when(roleRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(ro);
		roles.add(rol);
		Mockito.when(userRepository.save(ser)).thenReturn(ser);
		assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);	
		
	}
	
	@Test
	public void signUpTestManager() {
		SignupRequest user = new SignupRequest();
		user.setUsername("VRasasc");
		user.setEmail("vaascscr@gmail.com");
		user.setPassword("123456");
		User ser = new User("Vjr","vg@gmail.com","12439");
		Set<String> rr = new HashSet<>();
		rr.add("manager");
		user.setRole(rr);
		Set<Role> roles = new HashSet<>();
		Role rol = new Role();
		rol.setId("61ec5ce4cd7482b824b2164c");
		rol.setName(ERole.ROLE_MANAGER);
		Optional<Role> ro = null;
		Mockito.when(roleRepository.findByName(ERole.ROLE_MANAGER)).thenReturn(ro);
		roles.add(rol);
		Mockito.when(userRepository.save(ser)).thenReturn(ser);
		assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);	
		
	}
	
	@Test
	public void signUpTestUAdmin() {
		SignupRequest user = new SignupRequest();
		user.setUsername("VRasdasc");
		user.setEmail("vrasxa@gmail.com");
		user.setPassword("123456");
		User ser = new User("Vjr","vg@gmail.com","12439");
		Set<String> rr = new HashSet<>();
		rr.add("horse");
		user.setRole(rr);
		Set<Role> roles = new HashSet<>();
		Role rol = new Role();
		rol.setId("61ec5ce4cd7482b824b2164c");
		rol.setName(ERole.ROLE_USER);
		Optional<Role> ro = null;
		Mockito.when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(ro);
		roles.add(rol);
		Mockito.when(userRepository.save(ser)).thenReturn(ser);
		assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);	
		
	}

	
	@Test
	public void signInTest() throws IOException {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("manager_as");
		loginRequest.setPassword("123456");
		User user = new User("Vijay", "qwerty", "vijay@gmail.com");
		Mockito.when(userRepository.existsByUsername(loginRequest.getUsername())).thenReturn(true);
		Query query = Query.query(Criteria.where("username").is(loginRequest.getUsername()));
		Mockito.when(mongoTemplate.findOne(query, User.class)).thenReturn(user);
		assertTrue(authController.authenticateUser(loginRequest) instanceof ResponseEntity<?>);
	}
	
	@Test
	public void approvingLeaveNewLOP() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS40", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveNewML() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS41", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveNewPL() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS42", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveNewPaid() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS43", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveOldLOP() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS44", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveOldML() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS45", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveOldPL() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS46", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveOldPaid() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS47", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveExhLOP() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS48", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveExhML() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS49", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveExhPL() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS50", lev) instanceof String );

	}
	
	@Test
	public void approvingLeaveExhPaid() throws EmployeeNotFoundException {
		LeaveDefaults lev = new LeaveDefaults();
		assertTrue(apply.approveLeave("CS51", lev) instanceof String );

	}
	
	@Test
	public void viewEmployeeTest() {
		try {
		Mockito.doThrow(new EmployeeNotFoundException("Add operation not implemented"))
        .when(getseq).viewEmployees();
		}
		catch(Exception e) {
			assertThat(e)
            .isInstanceOf(EmployeeNotFoundException.class)
            .hasMessage("Employee not found");
		}
	}
	
	@Test
	public void viewResponsesTest() {
		try {
		Mockito.doThrow(new EmployeeNotFoundException("Add operation not implemented"))
        .when(getseq).viewResponses();
		}
		catch(Exception e) {
			assertThat(e)
            .isInstanceOf(EmployeeNotFoundException.class)
            .hasMessage("Employee not found");
		}
	}

}
