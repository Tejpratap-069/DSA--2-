public class Smart_Energy_Meter_CO2 {

    static int[] tree;
    static int[] arr = {18, 5, 14, 9, 7, 11, 6, 10};

    static void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = arr[start];
        } else {

            int mid = (start + end) / 2;

            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);

            tree[node] =
                    tree[2 * node] +
                    tree[2 * node + 1];
        }
    }

    static int query(int node, int start, int end,
                     int l, int r) {

        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node, start, mid, l, r)
                + query(2 * node + 1, mid + 1, end, l, r);
    }

    static void update(int node, int start, int end,
                       int idx, int val) {

        if (start == end) {
            arr[idx] = val;
            tree[node] = val;
            return;
        }

        int mid = (start + end) / 2;

        if (idx <= mid)
            update(2 * node, start, mid, idx, val);
        else
            update(2 * node + 1, mid + 1, end, idx, val);

        tree[node] =
                tree[2 * node] +
                tree[2 * node + 1];
    }

    public static void main(String[] args) {

        int n = arr.length;
        tree = new int[4 * n];

        build(1, 0, n - 1);

        System.out.println("Q1 Sum(1,5): "
                + query(1, 0, n - 1, 1, 5));

        System.out.println("Q2 Sum(0,7): "
                + query(1, 0, n - 1, 0, 7));

        update(1, 0, n - 1, 2, 20);

        System.out.println("Q3 Sum(1,5): "
                + query(1, 0, n - 1, 1, 5));
    }
}