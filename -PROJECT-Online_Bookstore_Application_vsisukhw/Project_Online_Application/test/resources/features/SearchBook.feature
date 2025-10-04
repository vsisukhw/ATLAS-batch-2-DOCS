Feature: Search Book
  As a customer
  I want to search for books by title or author
  So that I can find books I'm interested in

  Background:
    Given the bookstore has the following books:
      | title         | author           | price |
      | Clean Code    | Robert C. Martin | 45.99 |
      | Design Patterns | Gang of Four   | 54.99 |

  Scenario: User searches a book by title
    Given the bookstore has 'Clean Code'
    When the user searches for 'Clean Code'
    Then the system shows 'Clean Code' with author name 'Robert C. Martin'

  Scenario: User searches a book by author
    Given the bookstore has books by 'Robert C. Martin'
    When the user searches for 'Robert C. Martin'
    Then the system shows books by 'Robert C. Martin'

  Scenario: User searches for non-existent book
    When the user searches for 'Non-existent Book'
    Then the system shows no results