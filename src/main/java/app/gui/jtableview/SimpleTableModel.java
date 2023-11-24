package app.gui.jtableview;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.math3.util.Pair;

public class SimpleTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final String[ ] columnNames = {"Year","Value"};
    private List<Pair<Integer, Integer>> data; 
    private String requestName;

	public SimpleTableModel(String requestName, List<Pair<Integer, Integer>> data) {
		super();
		this.data = data;
		this.requestName = requestName;
	}
	
	@Override
	public int getRowCount() {
		return this.data.size();
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}
	@Override
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex<=getRowCount()-1)
			if(columnIndex == 0)
				return data.get(rowIndex).getFirst();
			else if(columnIndex == 1)
				return data.get(rowIndex).getSecond();

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	public String[] getColumnNames() {
		return columnNames;
	}

	public List<Pair<Integer, Integer>> getData() {
		return data;
	}

	public String getRequestName() {
		return requestName;
	}

	@Override
	public String toString() {
		return "SimpleTableModel [" +
				"requestName=" + requestName + 
				", columnNames=" + Arrays.toString(columnNames) + 
				", data=" + data.toString() + 
				"]";
	}

	
	
	
}
