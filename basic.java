
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import javax.swing.table.TableRowSorter;

import javax.swing.table.TableModel;

public class basic extends JFrame implements ActionListener {

	// this will create a new Panel with BorderLayout
	JPanel tblPanel = new JPanel(new BorderLayout());

	// this will create an array of JTextField with fixed size of 5
	JTextField[] txtFields = new JTextField[6];
	JTextField txtSearch; // remove the size specification from here

	// this will create an array of JLabel with fixed size of 5
	JLabel[] lblFields = new JLabel[6];

	// this string array will be the text of the Jlabel
	String[] strLblFields = { "Item Code ", "Item Description ", "Price", "Size", "Stocks", "Re-Order Point" };

	// this will create a JButton
	JButton btnStockin = new JButton();
	JButton btnAdd = new JButton();
	JButton btnEdit = new JButton();
	JButton btnEdit1 = new JButton();
	JButton btnDelete = new JButton();
	JButton btnSave = new JButton();
	JButton btnCancel = new JButton();

	String[] column = new String[] {
			"Item Code",
			"Item Description",
			"Price",
			"Size",
			"Stocks",
			"Re-Order",
			"Remarks"
	};

	
	String[][] row = new String[][] {
			{ "00001", "Noodles", "20", "50 grams", "200", "80", "High Stock" },
			// {"Pepito Manaloto", "32", "Munting Lupa","09123456789", "Male"}
	};

	DefaultTableModel model = new DefaultTableModel(row, column);

	JTable table = new JTable(model);

	JScrollPane scroll = new JScrollPane(table);

	Vector<String> r = new Vector<String>();

	DecimalFormat decimal = new DecimalFormat("00000");

	int decimalnum = 2;

	void basic() {

		this.setTitle("Table");
		this.setSize(1000, 450);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		int y = 50;
		// this loop will set the properties of JTextField , JLabel
		for (int i = 0; i <= lblFields.length - 1; i++) {

			// properties of JTextField
			txtFields[i] = new JTextField();
			txtFields[i].setBounds(180, y, 150, 25);
			txtFields[i].setEditable(false);
			this.add(txtFields[i]);

			// properties of JLabel
			lblFields[i] = new JLabel();
			lblFields[i].setText(strLblFields[i]);
			lblFields[i].setBounds(50, y, 150, 25);
			this.add(lblFields[i]);

			y += 30;
		}

		btnStockin.setBounds(50, 250, 90, 20);
		btnStockin.setText("Stock In");
		btnStockin.setFocusable(false);
		btnStockin.addActionListener(this);

		btnAdd.setBounds(145, 250, 90, 20);
		btnAdd.setText("ADD");
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(this);

		btnEdit.setBounds(240, 250, 90, 20);
		btnEdit.setText("Edit");
		btnEdit.setFocusable(false);
		btnEdit.addActionListener(this);
		btnEdit.setVisible(true);

		btnEdit1.setBounds(145, 280, 90, 20);
		btnEdit1.setText("Save");
		btnEdit1.setFocusable(false);
		btnEdit1.addActionListener(this);
		btnEdit1.setVisible(false);

		btnDelete.setBounds(50, 280, 90, 20);
		btnDelete.setText("Delete");
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(this);

		btnSave.setBounds(145, 280, 90, 20);
		btnSave.setText("Save");
		btnSave.setFocusable(false);
		btnSave.addActionListener(this);
		btnSave.setEnabled(false);

		btnCancel.setBounds(240, 280, 90, 20);
		btnCancel.setText("Cancel");
		btnCancel.setFocusable(false);
		btnCancel.addActionListener(this);
		btnCancel.setEnabled(false);
		txtSearch = new JTextField();
		txtSearch.setBounds(350, 0, 150, 20);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				filter();
			}

			public void removeUpdate(DocumentEvent e) {
				filter();
			}

			public void insertUpdate(DocumentEvent e) {
				filter();
			}

			public void filter() {
				String filterStr = txtSearch.getText();
				if (filterStr.length() == 0) {
					((TableRowSorter<DefaultTableModel>) table.getRowSorter()).setRowFilter(null);
				} else {
					((TableRowSorter<DefaultTableModel>) table.getRowSorter())
							.setRowFilter(RowFilter.regexFilter("(?i)" + filterStr, 1));
				}
			}
		});

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);

		table.getTableHeader().setEnabled(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < column.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		TableColumnModel tmtblProducts = table.getColumnModel();
		tmtblProducts.getColumn(0).setPreferredWidth(150);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				int rows = table.getSelectedRow();
				if (rows == -1) {

				}

				else {
					for (int i = 0; i <= txtFields.length - 1; i++) {
						txtFields[i].setText((String) table.getValueAt(rows, i));

					}
				}

			}

		});

		tblPanel.setBounds(350, 20, 600, 370);
		tblPanel.setBackground(Color.white);
		tblPanel.add(scroll);

		this.add(tblPanel);
		this.add(txtSearch);
		this.add(btnStockin);
		this.add(btnAdd);
		this.add(btnEdit);
		this.add(btnEdit1);
		this.add(btnDelete);
		this.add(btnSave);
		this.add(btnCancel);
		this.setLayout(null);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAdd) {

			txtFields[0].setText(decimal.format(decimalnum));
			txtFields[0].setEditable(false);
			decimalnum += 1;

			for (int i = 1; i <= txtFields.length - 1; i++) {
				txtFields[i].setEditable(true);
				txtFields[i].setText("");
			}

			btnAdd.setEnabled(false);
			btnStockin.setEnabled(false);
			btnDelete.setEnabled(false);
			btnEdit.setEnabled(false);

			btnSave.setEnabled(true);
			btnSave.setVisible(true); // make the save button visible
			btnCancel.setEnabled(true);

			r = new Vector<String>();
		}

		else if (e.getSource() == btnSave) {

			if (txtFields[0].getText().isBlank() || txtFields[1].getText().isBlank() || txtFields[2].getText().isBlank()
					|| txtFields[3].getText().isBlank() || txtFields[4].getText().isBlank()
					|| txtFields[5].getText().isBlank()) {
				JOptionPane.showMessageDialog(this, "Invalid/Empty inputs. Please try again.");
			}

			else if (txtFields[0].getText().equals("") || txtFields[1].getText().equals("")
					|| txtFields[2].getText().equals("") || txtFields[3].getText().equals("")
					|| txtFields[4].getText().equals("") || txtFields[5].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Invalid/Empty inputs. Please try again.");
			}

			else if (txtFields[0].getText().isEmpty() || txtFields[1].getText().isEmpty()
					|| txtFields[2].getText().isEmpty() || txtFields[3].getText().isEmpty()
					|| txtFields[4].getText().isEmpty() || txtFields[5].getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Invalid/Empty inputs. Please try again.");
			}

			else {

				try {
					int intconvertStock = Integer.parseInt(txtFields[4].getText());
					int intconvertReorder = Integer.parseInt(txtFields[5].getText());

					for (int i = 0; i <= 3; i++) {
						r.add(txtFields[i].getText());
					}

					r.add(String.valueOf(intconvertStock));
					r.add(String.valueOf(intconvertReorder));

					int dialog = JOptionPane.showConfirmDialog(this, "Do you want to save the row?", "Saving",
							JOptionPane.YES_NO_OPTION);

					if (dialog == 0) {

						if (intconvertStock < intconvertReorder) {
							r.add("Low Stock");
						}

						else if (intconvertStock == intconvertReorder) {
							r.add("Low Stock");
						}

						else if (intconvertStock > intconvertReorder) {
							r.add("High Stock");
						}

						model.addRow(r);

						JOptionPane.showMessageDialog(this, "Row Added");
						table.getSelectionModel().clearSelection();

						for (int a = 0; a <= txtFields.length - 1; a++) {
							txtFields[a].setText("");
							txtFields[a].setEditable(false);
						}

						btnAdd.setEnabled(true);
						btnCancel.setEnabled(true);
						btnStockin.setEnabled(true);
						btnDelete.setEnabled(true);
						btnEdit.setEnabled(true);

						btnSave.setEnabled(false);
						btnCancel.setEnabled(false);

					}

				} catch (Exception m) {
					JOptionPane.showMessageDialog(this, "Please enter number on stocks and re-order point.",
							"Unsupported Input", JOptionPane.WARNING_MESSAGE);

				}

			}

		}

		else if (e.getSource() == btnCancel) {

			int dialog = JOptionPane.showConfirmDialog(this, "Are you sure to cancel?", "Cancel...",
					JOptionPane.YES_NO_OPTION);

			if (dialog == 0) {
				table.getSelectionModel().clearSelection();

				for (int i = 0; i <= txtFields.length - 1; i++) {
					txtFields[i].setText("");
					txtFields[i].setEditable(false);
				}
				btnAdd.setEnabled(true);
				btnStockin.setEnabled(true);
				btnDelete.setEnabled(true);
				btnEdit.setEnabled(true);

				btnEdit1.setVisible(false);

				btnSave.setVisible(true);
				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);

			}

		}

		else if (e.getSource() == btnDelete) {

			int selectedrow = table.getSelectedRow();

			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Please select row.", "No row selected",
						JOptionPane.WARNING_MESSAGE);
			}

			else if (table.isRowSelected(selectedrow)) {
				int dialog = JOptionPane.showConfirmDialog(this, "Do you want to delete the selected row?",
						"Delete Row", JOptionPane.YES_NO_OPTION);

				if (dialog == 0) {
					model.removeRow(selectedrow);
					JOptionPane.showMessageDialog(this, "Row Deleted");

					for (int i = 0; i <= txtFields.length - 1; i++) {
						txtFields[i].setText("");
					}
					table.getSelectionModel().clearSelection();
				}

			}
		}

		else if (e.getSource() == btnStockin) {

			int rowselected = table.getSelectedRow();

			if (rowselected == -1) {
				JOptionPane.showMessageDialog(this, "Select row and try again.", "No row selected",
						JOptionPane.WARNING_MESSAGE);
			}

			else {

				try {
					String paststock = table.getValueAt(rowselected, 4).toString();
					String numstock = JOptionPane.showInputDialog("Enter Stocks:");

					int intPast = Integer.parseInt(paststock);
					int stockTotal = Integer.parseInt(numstock) + intPast;

					JOptionPane.showMessageDialog(this, "Stocks Added");
					model.setValueAt(String.valueOf(stockTotal), rowselected, 4);
					String getTbl = table.getValueAt(rowselected, 5).toString();

					if (stockTotal > Integer.parseInt(getTbl)) {
						model.setValueAt("High Stock", rowselected, 6);
					}

					else if (stockTotal == Integer.parseInt(getTbl)) {
						model.setValueAt("Low Stock", rowselected, 6);
					}

					else if (stockTotal < Integer.parseInt(getTbl)) {
						model.setValueAt("Low Stock", rowselected, 6);
					}

					for (int a = 0; a <= txtFields.length - 1; a++) {
						txtFields[a].setText("");

					}

					table.getSelectionModel().clearSelection();

				}

				catch (Exception jm) {
					JOptionPane.showMessageDialog(this, "Enter number on stocks. Please try again", "Input Error",
							JOptionPane.WARNING_MESSAGE);
					table.getSelectionModel().clearSelection();
					for (int i = 0; i <= txtFields.length - 1; i++) {
						txtFields[i].setText("");

					}
				}

			}

			//
		}

		else if (e.getSource() == btnEdit) {

			if (table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Please select row.", "No row selected",
						JOptionPane.WARNING_MESSAGE);
			}

			else {

				for (int i = 0; i <= txtFields.length - 1; i++) {
					txtFields[i].setEditable(true);
				}

				txtFields[4].setEditable(false);

				btnAdd.setEnabled(false);
				btnCancel.setEnabled(false);
				btnStockin.setEnabled(false);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);

				btnEdit1.setVisible(true);
				btnSave.setVisible(false);

				btnCancel.setEnabled(true);

			}

		}

		else if (e.getSource() == btnEdit1) {

			try {

				if (txtFields[0].getText().isBlank() || txtFields[1].getText().isBlank()
						|| txtFields[2].getText().isBlank() || txtFields[3].getText().isBlank()
						|| txtFields[4].getText().isBlank() || txtFields[5].getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "Empty textfields. Make sure to fill up the fields correctly.");
				}

				else {
					int numrow = table.getSelectedRow();
					int extractnum = Integer.parseInt(txtFields[4].getText());
					int extractnum1 = Integer.parseInt(txtFields[5].getText());

					for (int i = 0; i <= 3; i++) {

						model.setValueAt(txtFields[i].getText(), numrow, i);
					}

					model.setValueAt(String.valueOf(extractnum), numrow, 4);
					model.setValueAt(String.valueOf(extractnum1), numrow, 5);

					if (extractnum < extractnum1) {
						model.setValueAt("Low Stock", numrow, 6);
					}

					else if (extractnum > extractnum1) {
						model.setValueAt("High Stock", numrow, 6);
					}

					JOptionPane.showMessageDialog(this, "Row Edited.");
					table.getSelectionModel().clearSelection();

					btnAdd.setEnabled(true);
					btnCancel.setEnabled(true);
					btnStockin.setEnabled(true);
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);

					btnCancel.setEnabled(false);
					// btn[2].setVisible(true);
					btnEdit1.setVisible(false);
					btnSave.setVisible(true);
					btnSave.setEnabled(false);

					for (int i = 0; i <= txtFields.length - 1; i++) {
						txtFields[i].setText("");
						txtFields[i].setEditable(false);
					}
				}

			} catch (Exception r) {
				JOptionPane.showMessageDialog(this, "Please enter number on stocks and re-order point.",
						"Unsupported Input", JOptionPane.ERROR_MESSAGE);

			}

		}

	}

	
}