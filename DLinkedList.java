import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class DLinkedList extends JFrame{
    Node head;
    Node tail; 
  
    static class Node { 
        int data; 
        Node prev; 
        Node next;
      
        public Node(int d) 
        { 
            this.data = d; 
            this.prev = null; 
            this.next = null; 
        } 
    } 

    public static void addtoend(DLinkedList list, int data) {
        Node node = new Node(data);
        node.next = null;
        node.prev = null;

        if (list.tail == null){
            list.head = node;
            list.tail = node;
        }

        else {
            list.tail.next = node;
            node.prev = list.tail;
            list.tail = node;
        }

    }

    public static void addtostart(DLinkedList list, int data){
        Node node = new Node(data);
        node.next = null;
        node.prev = null;
        if (list.head == null){
            list.head = node;
            list.tail = node;
        } else {
            node.next = list.head;
            list.head.prev = node;
            list.head = node;
        }
    }

    public static void printlist(DLinkedList list) {
        Node node = list.head;

        if (node == null) {
            JOptionPane.showMessageDialog(null, "Лист пуст");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();

        while (node != null) {
            stringBuilder.append(node.data).append(" ");
            node = node.next;
        }

        JOptionPane.showMessageDialog(null, "\nСписок: " + stringBuilder.toString() +
                "\nhead: " + list.head.data + "\ntail: " + list.tail.data);
    }

    public static void clear(DLinkedList list){
        list.head = null;
        list.tail = null;
    }


    public static int length(DLinkedList list){
    Node node = list.head;
    int count = 0;
    if (node == null){
        JOptionPane.showMessageDialog(null,"Длина: 0");
        return 0;
    }   

    while (node != null){
        count++;
        node = node.next;
    }
        JOptionPane.showMessageDialog(null,"Длина списка: " + count);
        return count;
    }

    public static void deletefirst(DLinkedList list){
        if (list.head == null){
            return;
        }

        if (list.head.next == null){
            list.head = null;
            list.tail = null;
        }
        else {

            Node node = list.head;
            list.head = list.head.next;
            list.head.prev = null;
            node.next = null;

        }

    }

    public static boolean empty(DLinkedList list){
        return list.head == null;

    }

    public static void deletelast(DLinkedList list){
        if (list.tail == null){
            return;
        }

        if (list.head.next == null){
            list.head = null;
            list.tail = null;
        }
        else {

            Node node = list.tail;
            list.tail = list.tail.prev;
            list.tail.next = null;
            node.prev = null;

        }

    }

    public static void findindex(DLinkedList list, int value){
        Node node = list.head;
        int count = 0;
        while (node != null){
            if (node.data == value){
                JOptionPane.showMessageDialog(null,"Индекс элемента: " + count);
                return;
            }
            count++;
            node = node.next;
        }
        JOptionPane.showMessageDialog(null,"Элемент не найдет");
    }

    public static int findmin(DLinkedList list){
        Node node = list.head;
        int min = Integer.MAX_VALUE;
        while (node != null){
            if (node.data < min){
                min = node.data;
            }
            node = node.next;
        }
        return min;
    }


    public static int findmax(DLinkedList list){
        Node node = list.head;
        int max = Integer.MIN_VALUE;
        while (node != null){
            if (node.data > max){
                max = node.data;
            }
            node = node.next;
        }
        return max;
    }

    public static void deletevalue(DLinkedList list, int value) {
        Node node = list.head;

        while (node != null) {
            if (node.data == value) {
                Node prev = node.prev;
                Node next = node.next;

                if (prev != null) {
                    prev.next = next;
                } else {
                    list.head = next;
                }

                if (next == null) {
                    list.tail = prev;
                } else {
                    next.prev = prev;
                }

                return;
            }

            node = node.next;
        }

    }


    public static void deleteallvalue(DLinkedList list, int value) {
        Node node = list.head;

        while (node != null) {
            if (node.data == value) {
                Node prev = node.prev;
                Node next = node.next;

                if (prev != null) {
                    prev.next = next;
                } else {
                    list.head = next;
                }

                if (next == null) {
                    list.tail = prev;
                } else {
                    next.prev = prev;
                }

            }

            node = node.next;
        }
    }


    public static void changellvalue(DLinkedList list, int value, int newvalue) {
        Node node = list.head;

        while (node != null) {
            if (node.data == value) {
                node.data = newvalue;

            }

            node = node.next;
        }
    }

    public static boolean symmetric(DLinkedList list){
        if (list.head == null) {
            return true;
        }
        Node Pointer = list.head;
        Stack<Integer> stack = new Stack<>();

        while (Pointer != null) {
            stack.push(Pointer.data);
            Pointer = Pointer.next;
        }

        Pointer = list.head;

        while (Pointer != null) {
            if (Pointer.data != stack.pop()) {
                return false;
            }
            Pointer = Pointer.next;
        }

        return true;
    }

    public static boolean removetwotoorder(DLinkedList list) {
        int count = 0;
        Node node = list.head;

        while (node != null && node.next != null) {
            if (node.data > node.next.data) {
                count++;
            }
            if (count > 2) {
                return false;
            }
            node = node.next;
        }

        return true;
    }

    public static int uniquevalues(DLinkedList list){
        Set<Integer> uniqueValues = new HashSet<>();
        Node currNode = list.head;
        while (currNode != null) {
            uniqueValues.add(currNode.data);
            currNode = currNode.next;
        }
        return uniqueValues.size();
    }


    public static void deleteDuplicates(DLinkedList list) {
        Set<Integer> seen = new HashSet<>();
        Node node = list.head;
        Node prev = null;

        while (node != null) {
            if (seen.contains(node.data)) {
                prev.next = node.next;
            } else {
                seen.add(node.data);
                prev = node;
            }
            node = node.next;
        }

        Node tail = list.head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        list.tail = tail;

    }

    public static void reverse(DLinkedList list){
        Node node = list.tail;
        while (node != null){
            JOptionPane.showMessageDialog(null,node.data + " ");
            node = node.prev;
        }
        assert list.tail != null;
    }


    public static DLinkedList sortList(DLinkedList list) {
        if (list.head == null || list.head.next == null) {
            return list;
        }

        Node sorted = null;
        Node node = list.head;

        while (node != null) {
            Node next = node.next;

            if (sorted == null || sorted.data >= node.data) {
                node.next = sorted;
                node.prev = null;
                if (sorted != null) {
                    sorted.prev = node;
                }
                sorted = node;
            } else {
                Node temp = sorted;
                while (temp.next != null && temp.next.data < node.data) {
                    temp = temp.next;
                }
                node.next = temp.next;
                if (temp.next != null) {
                    temp.next.prev = node;
                }
                temp.next = node;
                node.prev = temp;
            }
            node = next;
        }

        list.head = sorted;

        Node temp = sorted;
        while (temp.next != null) {
            temp = temp.next;
        }
        list.tail = temp;
        return list;
    }





    public static DLinkedList bubbleSort(DLinkedList list) {
        if (list.head == null || list.head.next == null) {
            return list;
        }
        boolean swapped;
        do {
            swapped = false;
            Node node = list.head;
            while (node.next != null) {
                if (node.data > node.next.data) {
                    swap(node, node.next);
                    swapped = true;
                }
                node = node.next;
            }
        } while (swapped);
        return list;
    }

    private static void swap(Node node1, Node node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    public static void addbook(DLinkedList list, int value){
        Node node = list.head;
        Node newnode = new Node(value);
        if (node.data >= value){
            addtostart(list, value);
        }
        while (node.next != null){
            if (node.data <= value && value <= node.next.data){
                newnode.prev = node;
                newnode.next = node.next;
                node.next.prev = newnode;
                node.next = newnode;
                return;
            }
            node = node.next;
        }
        addtoend(list, value);
    }

    public static DLinkedList merge(DLinkedList list1, DLinkedList list2) {
        DLinkedList merged = new DLinkedList();
        int size1 = length(list1);
        int size2 = length(list2);
        int i = 0;
        int j = 0;

        if (size1 == 0) {
            return list2;
        }
        if (size2 == 0) {
            return list1;
        }

        Node node1 = list1.head;
        Node node2 = list2.head;

        while (i < size1 && j < size2 && node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                addtoend(merged,node1.data);
                node1 = node1.next;
                i++;
            } else {
                addtoend(merged,node2.data);
                node2 = node2.next;
                j++;
            }
        }

        while (i < size1 && node1 != null) {
            addtoend(merged,node1.data);
            node1 = node1.next;
            i++;
        }

        while (j < size2 && node2 != null) {
            addtoend(merged,node2.data);
            node2 = node2.next;
            j++;
        }

        return merged;
    }

    public static DLinkedList sortmorethanzero(DLinkedList list){
        DLinkedList sorted = new DLinkedList();
        Node node = list.head;

        while(node != null){

            if (node.data > 0){
                addtoend(sorted, node.data);
            }
            node = node.next;
        }

        return sorted;
    }

    public static DLinkedList sortevenindex(DLinkedList list){
        DLinkedList sorted = new DLinkedList();
        Node node = list.head;
        int count = 0;

        while(node != null){
            if (count % 2 != 0){
                addtoend(sorted, node.data);
            }
            count++;
            node = node.next;
        }

        return sorted;
    }


    public static boolean setsequals(DLinkedList list1,DLinkedList list2){

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        Node node1 = list1.head;
        Node node2 = list2.head;

        while (node1 != null){
            set1.add(node1.data);
            node1 = node1.next;
        }

        while (node2 != null){
            set2.add(node2.data);
            node2 = node2.next;
        }

        return set1.equals(set2);
    }

    public static void addprevfornew(DLinkedList list, int value) {
        if (empty(list)) {
            addtoend(list, value);
        } else {
            Node node = list.head;
            while (node != null) {
                StringBuilder prev = new StringBuilder();
                node.data = Integer.parseInt(prev.append(node.data).append(value).toString());
                node = node.next;
            }
        }
    }


    private void initializeUI() {
        DLinkedList list = new DLinkedList();
        setTitle("Двусвязный список");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setExtendedState(Frame.MAXIMIZED_BOTH);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton findindexButton = new JButton("Найти индекс элемента");
        JButton insertButton = new JButton("Добавить как последний элемент");
        JButton addFirstButton = new JButton("Добавить как первый элемент");
        JButton printListButton = new JButton("Распечатать");
        JButton deleteAllButton = new JButton("Удалить все");
        JButton countElementsButton = new JButton("Длина листа");
        JButton isEmptyButton = new JButton("Пустой лист");
        JButton deleteFirstButton = new JButton("Удалить первый элемент");
        JButton deleteLastButton = new JButton("Удалить последний элемент");
        Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
        buttonPanel.setBackground(Color.BLACK);
        findindexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(JOptionPane.showInputDialog("Введите значение:"));
                findindex(list, value);
            }
        });


        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int data = Integer.parseInt(JOptionPane.showInputDialog("Введите значение:"));
                addtoend(list, data);
            }
        });

        addFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int data = Integer.parseInt(JOptionPane.showInputDialog("Введите значение:"));
                addtostart(list, data);
            }
        });

        printListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printlist(list);
            }
        });

        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear(list);
            }
        });

        countElementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(list, "Длина листа: " + length(list));
            }
        });

        isEmptyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(list, "Проверка на пустой лист: " + empty(list));
            }
        });

        deleteFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletefirst(list);
            }
        });

        deleteLastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletelast(list);
            }
        });

        JButton deleteByValueButton = new JButton("Удалить по значению");
        deleteByValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(JOptionPane.showInputDialog("Введите значение для удаления:"));
                deletevalue(list, value);
            }
        });
        buttonPanel.add(deleteByValueButton);

        JButton deleteAllByValueButton = new JButton("Удалить все по значению");
        deleteAllByValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(JOptionPane.showInputDialog("Введите значение для удаления:"));
                deleteallvalue(list, value);
            }
        });
        buttonPanel.add(deleteAllByValueButton);
        JButton updateAllByValueButton = new JButton("Обновить все по значению");
        updateAllByValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldValue = Integer.parseInt(JOptionPane.showInputDialog("Введите старое значение:"));
                int newValue = Integer.parseInt(JOptionPane.showInputDialog("Введите новое значение:"));
                changellvalue(list, oldValue, newValue);
            }
        });
        buttonPanel.add(updateAllByValueButton);
        JButton isSymmetricButton = new JButton("Проверить на симметрию");
        isSymmetricButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(list, "Симметричен: " + symmetric(list));
            }
        });
        buttonPanel.add(isSymmetricButton);

        JButton canRemoveTwoElementsToOrderButton = new JButton("Можно удалить два элемента для упорядочивания");
        canRemoveTwoElementsToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(list, "Можно удалить два элемента: " + removetwotoorder(list));
            }
        });
        buttonPanel.add(canRemoveTwoElementsToOrderButton);

        JButton countUniqueValuesButton = new JButton("Подсчитать уникальные значения");
        countUniqueValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(list, "Уникальных значений: " + uniquevalues(list));
            }
        });
        buttonPanel.add(countUniqueValuesButton);

        JButton deleteDuplicatesButton = new JButton("Удалить дубликаты");
        deleteDuplicatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDuplicates(list);
            }
        });
        buttonPanel.add(deleteDuplicatesButton);

        JButton reverseListOrderButton = new JButton("Перевернуть порядок");
        reverseListOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverse(list);
            }
        });
        buttonPanel.add(reverseListOrderButton);


        JButton findMaxButton = new JButton("Max");
        findMaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int max = findmax(list);
                JOptionPane.showMessageDialog(list, "Максимальное значение: " + max);
            }
        });
        buttonPanel.add(findMaxButton);


        JButton findMinButton = new JButton("Min");
        findMinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int min = findmin(list);
                JOptionPane.showMessageDialog(list, "Минимальное значение: " + min);
            }
        });


        JButton addPrevForNewButton = new JButton("Добавить предыдущее значение для нового элемента");
        addPrevForNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(JOptionPane.showInputDialog("Введите значение для нового элемента:"));
                addprevfornew(list, value);
            }
        });
        buttonPanel.add(addPrevForNewButton);

        JButton sortMoreThanZeroButton = new JButton("Сортировать элементы больше нуля");
        sortMoreThanZeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLinkedList sortedList = sortmorethanzero(list);
                printlist(sortedList);
            }
        });
        buttonPanel.add(sortMoreThanZeroButton);

        JButton sortEvenIndexButton = new JButton("Сортировать элементы с четными индексами");
        sortEvenIndexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLinkedList sortedList = sortevenindex(list);
                printlist(sortedList);
            }
        });
        buttonPanel.add(sortEvenIndexButton);

        JButton setEqualsButton = new JButton("Проверить, равны ли два списка");
        setEqualsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLinkedList list2 = new DLinkedList();
                addtoend(list2, 1);
                addtoend(list2, 3);
                addtoend(list2, 4);
                boolean isEqual = setsequals(list, list2);
                JOptionPane.showMessageDialog(list, "Списки равны: " + isEqual);
            }
        });
        buttonPanel.add(setEqualsButton);

        JButton mergeListsButton = new JButton("Объединить два списка");
        mergeListsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLinkedList list2 = new DLinkedList();
                addtoend(list2, 1);
                addtoend(list2, 3);
                addtoend(list2, 4);
                DLinkedList mergedList = merge(list, list2);
                printlist(mergedList);
            }
        });
        buttonPanel.add(mergeListsButton);

        JButton sortListButton = new JButton("Сортировать список");
        sortListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLinkedList sortedList = sortList(list);
                printlist(sortedList);
            }
        });
        buttonPanel.add(sortListButton);

        JButton bubbleSortButton = new JButton("Сортировать пузырьком");
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLinkedList sortedList = bubbleSort(list);
                printlist(sortedList);
            }
        });
        buttonPanel.add(bubbleSortButton);

        JButton addBookButton = new JButton("Добавить книгу в список");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(JOptionPane.showInputDialog("Введите значение для новой книги:"));
                addbook(list, value);
            }
        });
        buttonPanel.add(addBookButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        stylizeButton(addPrevForNewButton, buttonFont, 400, 50);
        stylizeButton(sortMoreThanZeroButton, buttonFont, 400, 50);
        stylizeButton(sortEvenIndexButton, buttonFont, 400, 50);
        stylizeButton(setEqualsButton, buttonFont, 400, 50);
        stylizeButton(mergeListsButton, buttonFont, 400, 50);
        stylizeButton(sortListButton, buttonFont, 400, 50);
        stylizeButton(bubbleSortButton, buttonFont, 400, 50);
        stylizeButton(addBookButton, buttonFont, 400, 50);
        stylizeButton(reverseListOrderButton, buttonFont, 400, 50);

        stylizeInputDialog();
        buttonPanel.add(findMinButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(addFirstButton);
        buttonPanel.add(printListButton);
        buttonPanel.add(deleteAllButton);
        buttonPanel.add(countElementsButton);
        buttonPanel.add(isEmptyButton);
        buttonPanel.add(deleteFirstButton);
        buttonPanel.add(deleteLastButton);
        buttonPanel.add(findindexButton);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        stylizeButton(findMinButton, buttonFont, 500, 75);
        stylizeButton(findMaxButton, buttonFont, 300, 50);
        stylizeButton(insertButton, buttonFont, 300, 50);
        stylizeButton(addFirstButton, buttonFont, 300, 50);
        stylizeButton(printListButton, buttonFont, 300, 50);
        stylizeButton(deleteAllButton, buttonFont, 300, 50);
        stylizeButton(countElementsButton, buttonFont, 300, 50);
        stylizeButton(findindexButton,buttonFont, 300, 50);
        stylizeButton(isEmptyButton, buttonFont, 300, 50);
        stylizeButton(deleteFirstButton, buttonFont, 300, 50);
        stylizeButton(deleteLastButton, buttonFont, 300, 50);
        stylizeButton(deleteByValueButton, buttonFont, 300, 50);
        stylizeButton(deleteAllByValueButton, buttonFont, 300, 50);
        stylizeButton(updateAllByValueButton, buttonFont, 300, 50);
        stylizeButton(isSymmetricButton, buttonFont, 300, 50);
        stylizeButton(canRemoveTwoElementsToOrderButton, buttonFont, 300, 50);
        stylizeButton(countUniqueValuesButton, buttonFont, 300, 50);
        stylizeButton(deleteDuplicatesButton, buttonFont, 300, 50);
        stylizeButton(reverseListOrderButton, buttonFont, 300, 50);
        stylizeInputDialog();

    }

    private void stylizeInputDialog() {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("OptionPane.background", new Color(0, 0, 0));
        UIManager.put("OptionPane.foreground", new Color(0, 0, 0));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("TextField.background", new Color(0, 0, 0));
        UIManager.put("TextField.foreground", new Color(255, 255, 255));
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Button.background", new Color(50, 205, 50));
        UIManager.put("Button.foreground", new Color(255, 255, 255));
    }

    private void stylizeButton(JButton button, Font font, int width, int height) {
        button.setFont(font);
        button.setBackground(new Color(50, 50, 50));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, height));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 255, 255));
                button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                button.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 0, 0));
                button.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                button.setForeground(Color.white);
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DLinkedList linkedList = new DLinkedList();
            linkedList.initializeUI();
        });
    }
}

