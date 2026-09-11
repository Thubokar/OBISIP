	package com.example.library.controller;
	
	import com.example.library.entity.Fine;
	import com.example.library.entity.IssueRecord;
	import com.example.library.service.FineService;
	import com.example.library.service.IssueService;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	
	import java.util.List;
	
	@Controller
	public class AdminFineController {
	
	    private final FineService fineService;
	    private final IssueService issueService;
	
	    public AdminFineController(
	            FineService fineService,
	            IssueService issueService) {
	
	        this.fineService = fineService;
	        this.issueService = issueService;
	    }
	
	    @GetMapping("/admin/fines")
	    public String adminFines(Model model) {
	
	        // Get all currently issued books
	        List<IssueRecord> issuedBooks =
	                issueService.getAllIssuedBooks();
	
	        // Create or update fines for overdue books
	        fineService.updateOverdueFines(issuedBooks);
	
	        // Get all fines
	        List<Fine> fines = fineService.getAllFines();
	
	        model.addAttribute("fines", fines);
	
	        return "admin-fines";
	    }
	
	    @PostMapping("/admin/fines/{id}/pay")
	    public String markFineAsPaid(@PathVariable Long id) {
	
	        fineService.markAsPaid(id);
	
	        return "redirect:/admin/fines";
	    }
	}