/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.showcase.client.content.cell

import com.google.gwt.i18n.client.Constants
import com.google.gwt.core.client.GWT
import com.google.gwt.view.client.{HasData, ListDataProvider, ProvidesKey}
import scala.collection.JavaConversions._
import collection.mutable.{Set, HashSet, HashMap}
import java.util.Date
import util.Random

/**
 * ContactDatabase Companion Object
 */
object ContactDatabase {

  /**
   * A contact category.
   */
  case class Category(displayName: String)

  /**
   * The key provider that provides the unique ID of a contact.
   */
  val KEY_PROVIDER = new ProvidesKey[ContactInfo]() {
    override def getKey(item: ContactInfo): Object = null//if (item == null) null else item.id.asInstanceOf[Object]
  }

  //  private[this] var nextId = 0
  //
  //  def getNextId: Int = {
  //    val id = nextId
  //    nextId += 1
  //    id
  //  }

  /**
   * Information about a contact.
   */
  case class ContactInfo(id: Int,
                         address: String,
                         birthday: Date,
                         category: Category,
                         firstName: String,
                         lastName: String) extends Comparable[ContactInfo] {

    override def compareTo(o: ContactInfo): Int = {
      if (o == null || o.firstName == null) -1 else -o.firstName.compareTo(firstName)
    }

    val fullName: String = firstName + " " + lastName

    val age: Int = 42

//    @SuppressWarnings(Array("deprecation"))
//    val age: Int = {
//      val today = new Date();
//      val years = today.getYear() - birthday.getYear();
//      if (today.getMonth() > birthday.getMonth()
//        || (today.getMonth() == birthday.getMonth() && today.getDate() > birthday.getDate())) {
//        years - 1
//      } else {
//        years
//      }
//    }
  }

  /**
   * The constants used in this Content Widget.
   */
//  trait DatabaseConstants extends Constants {
//    def contactDatabaseCategories: List[String]
//  }

//  val FEMALE_FIRST_NAMES = List(
//    "Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan",
//    "Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna",
//    "Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica",
//    "Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Anna", "Rebecca", "Virginia",
//    "Kathleen", "Pamela", "Martha", "Debra", "Amanda", "Stephanie", "Carolyn", "Christine",
//    "Marie", "Janet", "Catherine", "Frances", "Ann", "Joyce", "Diane", "Alice", "Julie",
//    "Heather", "Teresa", "Doris", "Gloria", "Evelyn", "Jean", "Cheryl", "Mildred", "Katherine",
//    "Joan", "Ashley", "Judith", "Rose", "Janice", "Kelly", "Nicole", "Judy", "Christina",
//    "Kathy", "Theresa", "Beverly", "Denise", "Tammy", "Irene", "Jane", "Lori", "Rachel",
//    "Marilyn", "Andrea", "Kathryn", "Louise", "Sara", "Anne", "Jacqueline", "Wanda", "Bonnie",
//    "Julia", "Ruby", "Lois", "Tina", "Phyllis", "Norma", "Paula", "Diana", "Annie", "Lillian",
//    "Emily", "Robin", "Peggy", "Crystal", "Gladys", "Rita", "Dawn", "Connie", "Florence",
//    "Tracy", "Edna", "Tiffany", "Carmen", "Rosa", "Cindy", "Grace", "Wendy", "Victoria", "Edith",
//    "Kim", "Sherry", "Sylvia", "Josephine", "Thelma", "Shannon", "Sheila", "Ethel", "Ellen",
//    "Elaine", "Marjorie", "Carrie", "Charlotte", "Monica", "Esther", "Pauline", "Emma",
//    "Juanita", "Anita", "Rhonda", "Hazel", "Amber", "Eva", "Debbie", "April", "Leslie", "Clara",
//    "Lucille", "Jamie", "Joanne", "Eleanor", "Valerie", "Danielle", "Megan", "Alicia", "Suzanne",
//    "Michele", "Gail", "Bertha", "Darlene", "Veronica", "Jill", "Erin", "Geraldine", "Lauren",
//    "Cathy", "Joann", "Lorraine", "Lynn", "Sally", "Regina", "Erica", "Beatrice", "Dolores",
//    "Bernice", "Audrey", "Yvonne", "Annette", "June", "Samantha", "Marion", "Dana", "Stacy",
//    "Ana", "Renee", "Ida", "Vivian", "Roberta", "Holly", "Brittany", "Melanie", "Loretta",
//    "Yolanda", "Jeanette", "Laurie", "Katie", "Kristen", "Vanessa", "Alma", "Sue", "Elsie",
//    "Beth", "Jeanne"
//  )
//
//  val MALE_FIRST_NAMES = List(
//    "James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph",
//    "Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven",
//    "Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy",
//    "Jose", "Larry", "Jeffrey", "Frank", "Scott", "Eric", "Stephen", "Andrew", "Raymond",
//    "Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick", "Peter", "Harold", "Douglas",
//    "Henry", "Carl", "Arthur", "Ryan", "Roger", "Joe", "Juan", "Jack", "Albert", "Jonathan",
//    "Justin", "Terry", "Gerald", "Keith", "Samuel", "Willie", "Ralph", "Lawrence", "Nicholas",
//    "Roy", "Benjamin", "Bruce", "Brandon", "Adam", "Harry", "Fred", "Wayne", "Billy", "Steve",
//    "Louis", "Jeremy", "Aaron", "Randy", "Howard", "Eugene", "Carlos", "Russell", "Bobby",
//    "Victor", "Martin", "Ernest", "Phillip", "Todd", "Jesse", "Craig", "Alan", "Shawn",
//    "Clarence", "Sean", "Philip", "Chris", "Johnny", "Earl", "Jimmy", "Antonio", "Danny",
//    "Bryan", "Tony", "Luis", "Mike", "Stanley", "Leonard", "Nathan", "Dale", "Manuel", "Rodney",
//    "Curtis", "Norman", "Allen", "Marvin", "Vincent", "Glenn", "Jeffery", "Travis", "Jeff",
//    "Chad", "Jacob", "Lee", "Melvin", "Alfred", "Kyle", "Francis", "Bradley", "Jesus", "Herbert",
//    "Frederick", "Ray", "Joel", "Edwin", "Don", "Eddie", "Ricky", "Troy", "Randall", "Barry",
//    "Alexander", "Bernard", "Mario", "Leroy", "Francisco", "Marcus", "Micheal", "Theodore",
//    "Clifford", "Miguel", "Oscar", "Jay", "Jim", "Tom", "Calvin", "Alex", "Jon", "Ronnie",
//    "Bill", "Lloyd", "Tommy", "Leon", "Derek", "Warren", "Darrell", "Jerome", "Floyd", "Leo",
//    "Alvin", "Tim", "Wesley", "Gordon", "Dean", "Greg", "Jorge", "Dustin", "Pedro", "Derrick",
//    "Dan", "Lewis", "Zachary", "Corey", "Herman", "Maurice", "Vernon", "Roberto", "Clyde",
//    "Glen", "Hector", "Shane", "Ricardo", "Sam", "Rick", "Lester", "Brent", "Ramon", "Charlie",
//    "Tyler", "Gilbert", "Gene"
//  )
//
//  val LAST_NAMES = List(
//    "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
//    "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
//    "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen",
//    "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker",
//    "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips",
//    "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris",
//    "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper",
//    "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James",
//    "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross",
//    "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes",
//    "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander",
//    "Russell", "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan",
//    "Wallace", "Woods", "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis",
//    "Harrison", "Gibson", "Mcdonald", "Cruz", "Marshall", "Ortiz", "Gomez", "Murray", "Freeman",
//    "Wells", "Webb", "Simpson", "Stevens", "Tucker", "Porter", "Hunter", "Hicks", "Crawford",
//    "Henry", "Boyd", "Mason", "Morales", "Kennedy", "Warren", "Dixon", "Ramos", "Reyes", "Burns",
//    "Gordon", "Shaw", "Holmes", "Rice", "Robertson", "Hunt", "Black", "Daniels", "Palmer",
//    "Mills", "Nichols", "Grant", "Knight", "Ferguson", "Rose", "Stone", "Hawkins", "Dunn",
//    "Perkins", "Hudson", "Spencer", "Gardner", "Stephens", "Payne", "Pierce", "Berry",
//    "Matthews", "Arnold", "Wagner", "Willis", "Ray", "Watkins", "Olson", "Carroll", "Duncan",
//    "Snyder", "Hart", "Cunningham", "Bradley", "Lane", "Andrews", "Ruiz", "Harper", "Fox",
//    "Riley", "Armstrong", "Carpenter", "Weaver", "Greene", "Lawrence", "Elliott", "Chavez",
//    "Sims", "Austin", "Peters", "Kelley", "Franklin", "Lawson"
//  )

//  val STREET_NAMES = List(
//    "Peachtree", "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Tenth",
//    "Fourteenth", "Spring", "Techwood", "West Peachtree", "Juniper", "Cypress", "Fowler",
//    "Piedmont", "Juniper", "Main", "Central", "Currier", "Courtland", "Williams",
//    "Centennial", "Olympic", "Baker", "Highland", "Pryor", "Decatur", "Bell", "Edgewood",
//    "Mitchell", "Forsyth", "Capital"
//  )
//
//  val STREET_SUFFIX = List(
//    "St", "Rd", "Ln", "Blvd", "Way", "Pkwy", "Cir", "Ave"
//  )

  /**
   * The singleton instance of the database.
   */
//  private[this] var instance: ContactDatabase = null

  /**
   * Get the singleton instance of the contact database.
   *
   * @return the singleton instance
   */
//  def getContactDatabase: ContactDatabase = {
//    if (instance == null) {
//      instance = new ContactDatabase()
//    }
//
//    instance
//  }
}

/**
 * The data source for contact information used in the sample.
 */
class ContactDatabase private () {
//
//  import ContactDatabase._
//
//  /**
//   * The provider that holds the list of contacts in the database.
//   */
//  val dataProvider = new ListDataProvider[ContactInfo]()
//
//  val categories = {
//    //val constants: DatabaseConstants = GWT.create(classOf[DatabaseConstants])
//    //val catNames = constants.contactDatabaseCategories.map(new Category(_))
//    List("Family","Friends","Coworkers","Businesses","Contacts").map(new Category(_))
//  }
//
//  /**
//   * The map of contacts to her friends.
//   */
//  private[this] val friendsMap = new HashMap[Int, Set[ContactInfo]]()
//
//  // Generate initial data.
//  generateContacts(250);
//
//  /**
//   * Add a new contact.
//   *
//   * @param contact the contact to add.
//   */
//  def addContact(contact: ContactInfo) {
//    val contacts = dataProvider.getList;
//    // Remove the contact first so we don't add a duplicate.
//    contacts.remove(contact);
//    contacts.add(contact);
//  }
//
//  /**
//   * Add a display to the database. The current range of interest of the display
//   * will be populated with data.
//   *
//   * @param display a { @Link HasData}.
//   */
//  def addDataDisplay(display: HasData[ContactInfo]) {
//    dataProvider.addDataDisplay(display);
//  }
//
//  /**
//   * Generate the specified number of contacts and add them to the data
//   * provider.
//   *
//   * @param count the number of contacts to generate.
//   */
//  def generateContacts(count: Int) {
//    val contacts = dataProvider.getList
//    for (i <- 0 until count) {
//      contacts.add(createContactInfo())
//    }
//  }
//
//  /**
//   * Query all contacts for the specified category.
//   *
//   * @param category the category
//   * @return the list of contacts in the category
//   */
//  def queryContactsByCategory(category: Category): List[ContactInfo] = {
//    dataProvider
//      .getList
//      .filter(_.category == category)
//      .toList
//  }
//
//  /**
//   * Query all contacts for the specified category that begin with the specified
//   * first name prefix.
//   *
//   * @param category the category
//   * @param firstNamePrefix the prefix of the first name
//   * @return the list of contacts in the category
//   */
//  def queryContactsByCategoryAndFirstName(category: Category, firstNamePrefix: String): List[ContactInfo] = {
//    dataProvider
//      .getList
//      .filter(_.category == category)
//      .filter(_.firstName.startsWith(firstNamePrefix))
//      .toList
//  }
//
//  /**
//   * Query the list of friends for the specified contact.
//   *
//   * @param contact the contact
//   * @return the friends of the contact
//   */
//  def queryFriends(contact: ContactInfo): Set[ContactInfo] = {
//    friendsMap.getOrElseUpdate(contact.id, {
//      // Assign some random friends.
//      val friends = HashSet[ContactInfo]()
//
//      val numContacts = dataProvider.getList.size()
//      val friendCount = 2 + Random.nextInt(8)
//
//      for (i <- 0 until friendCount) {
//        friends.add(dataProvider.getList.get(Random.nextInt(numContacts)))
//      }
//
//      friends
//    })
//  }
//
//  /**
//   * Refresh all displays.
//   */
//  def refreshDisplays() {
//    dataProvider.refresh()
//  }
//
//  /**
//   * Create a new random {@link ContactInfo}.
//   *
//   * @return the new { @link ContactInfo}.
//   */
//  @SuppressWarnings(Array("deprecation"))
//  private[this] def createContactInfo(): ContactInfo = {
//    val category = nextValue(categories)
//    val lastName = nextValue(LAST_NAMES)
//
//    val firstName = if (Random.nextBoolean()) {
//      // Male.
//      nextValue(MALE_FIRST_NAMES)
//    } else {
//      // Female.
//      nextValue(FEMALE_FIRST_NAMES)
//    }
//
//    // Create a birthday between 20-80 years ago.
//    val year = (new Date()).getYear() - 21 - Random.nextInt(61)
//    val birthday = (new Date(year, Random.nextInt(12), 1 + Random.nextInt(31)))
//
//    // Create an address.
//    val addrNum = 1 + Random.nextInt(999)
//    val addrStreet = nextValue(STREET_NAMES)
//    val addrSuffix = nextValue(STREET_SUFFIX)
//    val address = addrNum + " " + addrStreet + " " + addrSuffix
//
//    ContactInfo(getNextId, address, birthday, category, firstName, lastName)
//  }
//
//  /**
//   * Get the next random value from an array.
//   *
//   * @param array the array
//   * @return a random value in the array
//   */
//  private[this] def nextValue[T](array: List[T]): T = {
//    array(Random.nextInt(array.length))
//  }
}
