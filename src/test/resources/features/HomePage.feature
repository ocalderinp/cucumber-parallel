@regression
Feature: Home page
  Home page should work

  Scenario Outline: Home page shoul open
    Given a user opens a browser with specific configuration "<browserName>", "<browserVersion>" y "<plataforma>"
    When a user goes to home page
    Then the user sees the home page loaded
    Examples:
      | browserName | browserVersion | plataforma  |
      | Chrome      | latest         | Windows 10  |
      | Chrome      | latest-1       | Windows 10  |
      | Chrome      | latest-1       | Windows 8.1 |
      | Firefox     | latest         | Windows 8.1 |
      | Firefox     | latest         | Windows 10  |
      | Safari      | latest         | macOS 10.13 |