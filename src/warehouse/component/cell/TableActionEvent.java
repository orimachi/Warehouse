/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package warehouse.component.cell;


public interface TableActionEvent {
    public void onEdit(int row);

    public void onDelete(int row);
}
