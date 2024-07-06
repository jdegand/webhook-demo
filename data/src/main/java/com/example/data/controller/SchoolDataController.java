package com.example.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.example.data.exception.SchoolDataNotFoundException;
import com.example.data.model.SchoolData;
import com.example.data.model.Student;
import com.example.data.model.WebhookDetails;
import com.example.data.repository.SchoolDataRepository;

@RestController
@RequestMapping("/schools")
public class SchoolDataController {

	private SchoolDataRepository schoolDataRepository;

	public SchoolDataController(SchoolDataRepository schoolDataRepository) {
		this.schoolDataRepository = schoolDataRepository;
	}

	@PostMapping(path = "/addNewSchool")
	public SchoolData addNewSchool(@RequestParam String schoolName) {
		SchoolData schoolData = new SchoolData();
		schoolData.setSchoolName(schoolName);
		schoolDataRepository.save(schoolData);
		return schoolData;
	}

	@PostMapping(path = "/addWebhookEvent/{schoolId}")
	public String addWebhookEvent(@PathVariable Integer schoolId,
			@RequestBody WebhookDetails webhookDetails) {

		SchoolData schoolData = schoolDataRepository.findById(schoolId)
				.orElseThrow(() -> new SchoolDataNotFoundException("School with ID " + schoolId + " not found"));

		WebhookDetails details = new WebhookDetails();
		details.setEventName(webhookDetails.getEventName());
		details.setEndPointUrl(webhookDetails.getEndPointUrl());
		details.setSchoolData(schoolData);

		schoolData.getWebhookDetails().add(details);
		schoolDataRepository.save(schoolData);

		return "Webhook Added";
	}

	@PostMapping(path = "/addStudent/{schoolId}")
	public String addStudent(@PathVariable Integer schoolId, @RequestBody Student reqData) {
		SchoolData schoolData = schoolDataRepository.findById(schoolId)
				.orElseThrow(() -> new SchoolDataNotFoundException("School with ID " + schoolId + " not found"));

		Student student = new Student();
		student.setAge(reqData.getAge());
		student.setName(reqData.getName());
		student.setSchoolData(schoolData);

		schoolData.getStudents().add(student);

		schoolDataRepository.save(schoolData);

		WebhookDetails webhookDetails = schoolData.getWebhookDetails().stream()
				.filter(eventData -> "add".equals(eventData.getEventName()))
				.findFirst()
				.orElse(null);

		if (webhookDetails != null && webhookDetails.getEndPointUrl() != null) { // should do a better job validating
																					// URL
			String url = webhookDetails.getEndPointUrl(); // localhost:9000/
			url += "/" + reqData.getName();
			RestTemplate restTemplate = new RestTemplate();
			// String result = restTemplate.getForObject(url, String.class);

			RestClient restClient = RestClient.create(restTemplate);

			ResponseEntity<String> response = restClient
					.get()
					.uri(url)
					.retrieve()
					.toEntity(String.class);

			String result = response.getBody();

			System.out.println("result: " + result);
		}
		return "Student Added";
	}

}
