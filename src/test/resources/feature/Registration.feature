Feature: Registration functionality
  As a new customer,
  I want to create an account by registering with my personal details,
  So that I can login in the website.

  Scenario: Successful registration

    Given I am on the registration page
    When I register with valid details:
      | userName | Christoo ttd|
      | email| tti@ello.com |
      | password | ^Strong-Pass123 |
    Then I should be redirected to the dashboard and see welcome message

  Scenario: Registration with existing email
    Given I am on the registration page
    When I register with email that already exists:
      | userName | Chris tester|
      | email| tester@example.com |
      | password | ^Strong-Pass123 |
    Then I should see the error message Email already exists