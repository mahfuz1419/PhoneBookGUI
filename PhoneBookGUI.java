import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class PhoneBookGUI implements ActionListener, WindowListener {

    private ArrayList<Person> phoneBook;
    private Frame frame;
    private Panel left, right, lleft, lright;
    private Button add, showAll, search, clear;
    private TextField name, address, phone, searchBox;
    private Label nameLabel, addressLabel, phoneLabel;
    private TextArea searchResults;

    public PhoneBookGUI() {
        phoneBook = new ArrayList<>();
        frame = new Frame("Phone Book Application");

        // Initialize components
        left = new Panel();
        right = new Panel();
        lleft = new Panel();
        lright = new Panel();
        add = new Button("Add");
        showAll = new Button("Show All");
        search = new Button("Search");
        clear = new Button("Clear");
        name = new TextField(15);
        address = new TextField(15);
        phone = new TextField(15);
        searchBox = new TextField(15);
        nameLabel = new Label("Name:");
        addressLabel = new Label("Address:");
        phoneLabel = new Label("Phone:");
        searchResults = new TextArea(15, 35);

        Color buttonColor = new Color(211, 215, 217);
        Color labelColor = new Color(0, 0, 0);
        Color textFieldColor = new Color(188, 203, 226);

        left.setBackground(Color.LIGHT_GRAY);
        right.setBackground(Color.BLACK);
        lleft.setBackground(Color.WHITE);
        lright.setBackground(Color.lightGray);

        add.setBackground(buttonColor);
        showAll.setBackground(buttonColor);
        search.setBackground(buttonColor);
        clear.setBackground(buttonColor);

        nameLabel.setForeground(labelColor);
        addressLabel.setForeground(labelColor);
        phoneLabel.setForeground(labelColor);

        name.setBackground(textFieldColor);
        address.setBackground(textFieldColor);
        phone.setBackground(textFieldColor);
        searchBox.setBackground(textFieldColor);
        searchResults.setBackground(textFieldColor);



        // Add action listeners
        add.addActionListener(this);
        showAll.addActionListener(this);
        search.addActionListener(this);
        clear.addActionListener(this);

        // Add components to panels
        lleft.setLayout(new GridLayout(3, 2));
        lleft.add(nameLabel);
        lleft.add(name);
        lleft.add(addressLabel);
        lleft.add(address);
        lleft.add(phoneLabel);
        lleft.add(phone);

        lright.setLayout(new GridLayout(4, 1));
        lright.add(add);
        lright.add(showAll);
        lright.add(searchBox);
        lright.add(search);

        left.setLayout(new GridLayout(2, 1)); // Change layout to GridLayout
        left.add(lleft);
        left.add(lright);

        right.setLayout(new BorderLayout());
        right.add(searchResults, BorderLayout.CENTER);
        right.add(clear, BorderLayout.SOUTH);

        // Add panels to frame
        frame.setLayout(new GridLayout(1, 2));
        frame.add(left);
        frame.add(right);

        frame.addWindowListener(this);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    // ActionListener methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            addPerson();
        } else if (e.getSource() == showAll) {
            showAllContacts();
        } else if (e.getSource() == search) {
            searchContacts();
        } else if (e.getSource() == clear) {
            clearSearchResults();
        }
    }

    private void addPerson() {
        String personName = name.getText();
        String personAddress = address.getText();
        String personPhone = phone.getText();
        Person newPerson = new Person(personName, personAddress, personPhone);
        phoneBook.add(newPerson);
        clearFields();
    }

    private void showAllContacts() {
        searchResults.setText(""); // Clear existing text
        for (Person person : phoneBook) {
            searchResults.append("Name: " + person.getName() + "\n");
            searchResults.append("Address: " + person.getAddress() + "\n");
            searchResults.append("Phone: " + person.getPhone() + "\n\n");
        }
    }

    private void searchContacts() {
        String searchTerm = searchBox.getText();
        searchResults.setText(""); // Clear existing text
        for (Person person : phoneBook) {
            if (person.getName().equalsIgnoreCase(searchTerm)) {
                searchResults.append("Name: " + person.getName() + "\n");
                searchResults.append("Address: " + person.getAddress() + "\n");
                searchResults.append("Phone: " + person.getPhone() + "\n\n");
            }
        }
    }

    private void clearSearchResults() {
        searchResults.setText("");
    }

    private void clearFields() {
        name.setText("");
        address.setText("");
        phone.setText("");
    }

    // WindowListener methods
    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}



    public static void main(String[] args) {
        new PhoneBookGUI();
    }
}

class Person {
    private String name;
    private String address;
    private String phone;

    public Person(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}