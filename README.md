
Form before valid submission
![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/6b7b00aa-96be-41ec-a6ad-32132ec1f573)

Confirmation page after valid submission
![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/79a8867b-c1f1-4a3b-9fc1-1e6e12ada86e)
Payars Serve console output or valid submission
![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/07eac57f-2607-4dc8-8041-2d1195db58d8)

Form beore bad input submission
![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/991c9555-98ac-4423-afb0-a4bbb64009c5)


Form after bad input submission displaying error messages
![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/a0158385-0f02-49da-b148-8b02232b96e7)

Parayara server console output for invalid submission
![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/385cc0e3-e491-42d6-bedf-ffac7bb80aa5)

1) Your understanding of the difference between the forward and redirect operations. 
 forward:
A request that is internally routed to another resource is an example of an internal server activity. involves just one request-response cycle, and the internal procedure is hidden from the client. The browser's URL stays the same. frequently employed for an application's internal navigation.
Redirect:
involves telling the client to submit a new request to a different URL through communication. involves two cycles of requests and responses, and the browser modifies the URL in accordance. frequently employed in situations when material has been transferred permanently or for external redirects.
2) How would you be validating user submissions without the Bean Validation API standard?
Developers must provide their own unique validation logic inside the application's codebase if the Bean Validation API is not available. This means developing custom validation techniques to make sure user submissions meet predetermined standards. This method adds more lines of code and may present maintenance issues, but it also allows for customization of validations to meet unique requirements. Code redundancy could occur in the absence of a consistent validation API, therefore careful management is essential to preserving consistency throughout the application's various components. Custom logic offers flexibility, but it also may result in code duplication and require more work during development and maintenance.
3) How do you think this approach would scale to a real application with 100's of entities?
Scalability issues could arise if hundreds of entities in a real application had specific validation logic implemented. Errors, redundant code, and complicated maintenance are increased when validations are created and managed manually for every entity. The effort needed to maintain efficient and consistent validation across several entities increases significantly as the program grows. By offering a uniform and declarative approach, easing the load of manual validation implementation, and improving maintainability in large-scale applications, a standardized validation API—like the Bean Validation API—offers a more scalable alternative.
4) Why didn't we need to include any additional dependencies (i.e. Bean Validation, JDBC) in this project?
It doesn't provide the exact project context, but it offers some options if you didn't want extra requirements like JDBC or Bean Validation. First off, since it doesn't call for in-depth validation or direct database interfaces, the project might be rather straightforward. As an alternative, it's possible that the project is making use of a platform or framework that manages some functions by default, reducing the need for explicit dependencies. The lack of further dependencies could also point to a purposeful design decision, like depending on language characteristics or specially made implementations that meet the demands of the project. In general, the objectives and nature of the project determine if external dependencies are required.
