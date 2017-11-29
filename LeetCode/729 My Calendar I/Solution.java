class MyCalendar {
        TreeMap<Integer, Integer> calTree;
    public MyCalendar() {
        calTree = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer floorKey = calTree.floorKey(start);
        if (floorKey != null && calTree.get(floorKey) > start)
            return false;
        Integer ceilingKey = calTree.ceilingKey(start);
        if (ceilingKey != null && ceilingKey < end)
            return false;

        calTree.put(start, end);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
