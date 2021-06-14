package wordchain;

public final class WordChainConstants {

	public WordChainConstants() {}

    // Slots name
    public static final String SLOT_WORD         = "country";
    
    // Sesssion attributes
    public static final String ATT_INTERVAL         = "att_interval";

    // response string for various cases
    public static final String GOODBYE_RESPONSE     = "Goodbye";
    public static final String TITLE     = "Word Chain";
    public static final String CATEGORIES     = "animal or country";

    public static final String WELCOME_RESPONSE     = 
        "Welcome to "+TITLE+" Word Game. Say let's play"; 
    public static final String WELCOME_REPROMPT     = 
        "I will give you a "+CATEGORIES+" and you have to " +
        "name another "+CATEGORIES+" starting with the letter previous word ends. To start say let's play. ";
    public static final String REQUEST_REPROMPT     = 
        "Do you want to play new game? You can try help";
    public static final String PARSE_ERROR          = 
        "I am sorry, I did not understood your query. Please try help";
    public static final String INVALID_WORD      =
        "The word is invalid";    
    public static final String ALREADY_DONE_WORD = 
        "The word has already been spoken.";
    public static final String HELP_RESPONSE        =  WELCOME_REPROMPT ;
    public static final String TRY_AGAIN            = 
        "It is taking a lot of time. Do you want me to try again?";

    // Sample countries used to play game
    public static final String[] words = {"Afghanistan","Albania","Algeria","American Samoa",
    "Andorra","Angola","Anguilla","Antarctica","Antigua And Barbuda","Argentina","Armenia","Aruba",
    "Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus",
    "Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia And Herzegovina","Botswana",
    "Bouvet Island","Brazil", "British Indian Ocean Territory","Brunei Darussalam","Bulgaria",
    "Burkina Faso","Burundi", "Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands",
    "Central African Republic","Chad","Chile","China","Christmas Island","Cocos Islands","Colombia",
    "Comoros","Congo","Congo","Cook Islands","Costa Rica", "Cote D'ivoire","Croatia","Cuba","Cyprus",
    "Czech Republic","Denmark", "Djibouti","Dominica","Dominican Republic","East Timor","Ecuador",
    "Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia", "Falkland Islands",
    "Faroe Islands","Fiji","Finland","France", "French Guiana","French Polynesia",
    "French Southern Territories","Gabon","Gambia", "Georgia","Germany","Ghana","Gibraltar","Greece",
    "Greenland","Grenada", "Guadeloupe","Guam","Guatemala","Guinea","Guinea-bissau","Guyana","Haiti",
    "Heard Island And Mcdonald Islands","Holy See","Honduras","Hong Kong", "Hungary","Iceland","India",
    "Indonesia","Iran, Islamic Republic Of","Iraq", "Ireland","Israel","Italy","Jamaica","Japan","Jordan",
    "Kazakstan","Kenya", "Kiribati","Korea","Korea, Republic Of","Kosovo","Kuwait", "Kyrgyzstan",
    "Lao Peoples Democratic Republic","Latvia","Lebanon","Lesotho","Liberia", "Libyan Arab Jamahiriya",
    "Liechtenstein","Lithuania","Luxembourg","Macau", "Macedonia","Madagascar","Malawi","Malaysia",
    "Maldives","Mali","Malta","Marshall Islands","Martinique","Mauritania", "Mauritius","Mayotte",
    "Mexico","Micronesia","Moldova", "Monaco","Mongolia","Montserrat","Montenegro","Morocco","Mozambique",
    "Myanmar", "Namibia","Nauru","Nepal","Netherlands","New Caledonia", "New Zealand","Nicaragua","Niger",
    "Nigeria","Niue","Norfolk Island", "Northern Mariana Islands","Norway","Oman","Pakistan","Palau",
    "Palestinian Territory","Panama","Papua New Guinea","Paraguay","Peru", "Philippines","Pitcairn",
    "Poland","Portugal","Puerto Rico","Qatar","Reunion", "Romania","Russian Federation","Rwanda",
    "Saint Helena","Saint Kitts And Nevis", "Saint Lucia","Saint Pierre And Miquelon",
    "Saint Vincent And The Grenadines","Samoa", "San Marino","Sao Tome And Principe","Saudi Arabia",
    "Senegal","Serbia","Seychelles", "Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands",
    "Somalia", "South Africa","South Georgia And The South Sandwich Islands","Spain","Sri Lanka",
    "Sudan","Suriname","Svalbard And Jan Mayen","Swaziland","Sweden","Switzerland", "Syrian Arab Republic",
    "Taiwan","Tajikistan", "Tanzania","Thailand","Togo","Tokelau","Tonga","Trinidad And Tobago", "Tunisia",
    "Turkey","Turkmenistan","Turks And Caicos Islands","Tuvalu","Uganda", "Ukraine","United Arab Emirates",
    "United Kingdom","United States", "Uruguay","Uzbekistan","Vanuatu", "Venezuela", "Viet Nam",
    "Virgin Islands", "Wallis And Futuna","Western Sahara","Yemen","Zambia","Zimbabwe",
    "Aardvark", "Aardwolf", "Albatross",
    "Alligator", "Alpaca","Amphibian","Anaconda","Angelfish","Anglerfish","Ant","Anteater",
    "Antelope","Antlion","Ape","Aphid","Arctic Fox","Arctic Wolf","Armadillo",
    "Arrow crab","Asp","Baboon","Badger","Bald eagle","Bali cattle","Bandicoot",
    "Barnacle","Barracuda","Basilisk","Bass","Bat","Bear","Beaver",
    "Bedbug","Bee","Beetle","Bird","Bison","Black panther","Black widow spider",
    "Blackbird","Blue bird","Blue jay","Blue whale","Boa","Boar","Bobcat",
    "Bobolink","Bonobo","Booby","Bovid","Box jellyfish","Buffalo","Bug",
    "Butterfly","Buzzard","Camel","Canary","Canid","Canidae","Capybara",
    "Cardinal","Caribou","Carp","Cat","Caterpillar","Catfish","Catshark",
    "Cattle","Centipede","Cephalopod","Chameleon","Cheetah","Chickadee","Chicken",
    "Chicken breeds","Chimpanzee","Chinchilla","Chipmunk","Cicada","Clam","Clownfish",
    "Cobra","Cockroach","Cod","Condor","Constrictor","Coral","Cougar",
    "Cow","Coyote","Crab","Crane","Crane fly","Crawdad","Crayfish",
    "Cricket","Crocodile","Crow","Cuckoo","Damselfly","Deer","Dingo",
    "Dinosaur","Dog","Dolphin","Donkey","Dormouse","Dove","Dragon",
    "Dragonfly","Duck","Eagle","Earthworm","Earwig","Echidna","Eel",
    "Egret","Elephant","Elephant seal","Elk","Emu","English pointer","Ermine",
    "Falcon","Felidae","Ferret","Finch","Firefly","Fish","Flamingo",
    "Flea","Fly","Flyingfish","Fowl","Fox","Frog","Fruit bat",
    "Galliform","Gamefowl","Gayal","Gazelle","Gecko","Gerbil","Gibbon",
    "Gila monster","Giraffe","Goat","Goldfish","Goose","Gopher","Gorilla",
    "Grasshopper","Grouse","Guan","Guanaco","Guinea pig","Guineafowl","Gull",
    "Guppy","Haddock","Halibut","Hamster","Hare","Harrier","Hawk",
    "Hedgehog","Hermit crab","Heron","Herring","Hippopotamus","Hookworm","Hornet",
    "Horse","Hoverfly","Hummingbird","Hyena","Iguana","Impala","Jackal",
    "Jaguar","Jay","Jellyfish","Junglefowl","Kangaroo","Kangaroo rat","Kingfisher",
    "Kite","Kiwi","Koala","Koi","Krill","Lab rat","Ladybug",
    "Lama","Lamprey","Land snail","Landfowl","Lark","Leech","Lemming",
    "Lemur","Leopard","Leopon","Limpet","Lion","Lizard","Lobster",
    "Locust","Loon","Louse","Lungfish","Lynx","Macaw","Mackerel",
    "Magpie","Mammal","Manatee","Mandrill","Manta ray","Marlin","Marmoset",
    "Marmot","Marsupial","Marten","Mastodon","Meadowlark","Meerkat","Mink",
    "Minnow","Mite","Mockingbird","Mole","Mollusk","Mongoose","Monkey",
    "Moose","Mosquito","Moth","Mouse","Mule","Muskox","Narwhal",
    "Newt","Nightingale","Ocelot","Octopus","Opossum","Orangutan","Orca",
    "Ostrich","Otter","Owl","Ox","Panda","Panther","Panthera",
    "Parakeet","Parrot","Parrotfish","Partridge","Peacock","Peafowl","Pelican",
    "Penguin","Perch","Pheasant","Pig","Pigeon","Pike","Pilot whale",
    "Pinniped","Piranha","Planarian","Platypus","Polar bear","Pony","Porcupine",
    "Porpoise","Possum","Prairie dog","Prawn","Praying mantis","Primate","Ptarmigan",
    "Puffin","Puma","Python","Quail","Quelea","Quokka","Rabbit",
    "Raccoon","Rainbow trout","Rat","Rattlesnake","Raven","Ray","Red panda",
    "Reindeer","Reptile","Rhinoceros","Right whale","Roadrunner","Robin","Rodent",
    "Rook","Rooster","Roundworm","Sailfish","Salamander","Salmon","Sawfish",
    "Scale insect","Scallop","Scorpion","Sea lion","Seahorse","Shark","Sheep",
    "Shrew","Shrimp","Silkmoth","Silkworm","Silverfish","Skink","Skunk",
    "Sloth","Slug","Smelt","Snail","Snake","Snipe","Sole",
    "Sparrow","Sperm whale","Spider","Spider monkey","Spoonbill","Squid","Squirrel",
    "Starfish","Steelhead trout","Stingray","Stoat","Stork","Sturgeon","Sugar glider",
    "Swallow","Swan","Swift","Swordfish","Swordtail","Tahr","Takin",
    "Tapir","Tarantula","Tarsier","Tasmanian devil","Termite","Tern","Thrush",
    "Tick","Tiger","Tiger shark","Tiglon","Toad","Tortoise","Toucan",
    "Tree frog","Trout","Tuna","Turkey","Turtle","Tyrannosaurus","Urial",
    "Vicuna","Viper","Vole","Vulture","Wallaby","Walrus","Warbler",
    "Wasp","Water Boa","Water buffalo","Weasel","Whale","Whippet","Whitefish",
    "Whooping crane","Wildcat","Wildebeest","Wildfowl","Wolf","Wolverine","Wombat",
    "Woodpecker","Worm","Wren","X-ray fish","Xerinae","Yak","Yellow perch",
    "Zebra","Zebra finch"
 };
}