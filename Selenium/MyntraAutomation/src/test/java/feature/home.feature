Feature: User accessing the home page and searching a product

  @regression
  Scenario Outline: User searching other than characters in the search bar of the home page
    Given Home page is given to the user and search field is also provided
    When user searches a <item>
    Then Error page should be redirected
    Examples:
      | item             |
      | "<h1>hello</h1>" |
      | "1232***"        |

  @sanity
  Scenario: User searching a shirt in the search bar of home page
    Given Home page is given to the user and search field is also provided
    When user searches a "shirt"
    Then page is redirecting to another page with displaying shirts


  @sanity
  Scenario: User filters the product and getting the result
    When  user selects filter option user choose filter by "Men"
    Then The details has been filtered

  @sanity
  Scenario: User sorts the product by clicking sort option and getting the result.
    When user selects sort option filter has been displayed and user choose sort by "Customer Rating"
    Then user clicks "Customer Rating"


  @sanity
  Scenario: User viewing the entire product details
    When User clicking particular product
    Then Details about the product is displayed

    When User Selects their size
    Then confirmaiton of the size need to be displayed

    When User clicks Go To Bag button
    Then Bag confirmaiton popup need to be displayed

  @sanity
  Scenario: User confirms payment by Click add to bag option after he/she adding the product to the bag
    When User clicks add to bag option
    Then confirmation page has been displayed