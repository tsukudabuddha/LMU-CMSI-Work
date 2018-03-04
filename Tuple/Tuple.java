public class Tuple {
		private int[] tuple;
		private int length = 0;

    /** Constructs a k-tuple with all elements set to zero. */
    public Tuple (int k) {
        int[] array = new int[k];
				for( int i = 0; i < array.length; i++ ) {
					  array[i] = 0;
				}
				this.length = k;
				this.tuple = array;
		}

    /** Constructs a tuple from array data.*/
		public Tuple (int[] data) {
        int[] array = new int[data.length];
				int temp = 0;
				for( int i = 0; i < array.length; i++ ) {
					  array[i] = data[i];
				}
				this.length = data.length;
				this.tuple = array;
		}
    /** Classwide method which returns a tuple constructed from the given array. */
		public static Tuple makeTupleFromData (int[] data){
        return new Tuple(data);
		}

		/** Returns a new tuple whose elements are the sums of this tuple and tuple t. Throws an IllegalArgumentException if the tuples are not the same length. */
		public Tuple add (Tuple t){
			  Tuple sum = new Tuple(this.length);
				if(this == null || t == null){
					  return null;
				}
        else if(t.length() != this.length()){
					  throw new IllegalArgumentException("The tuples should be the same length");
				} else {
					  for( int i = 0; i < t.length; i++ ) {
							 sum.tuple[i] = t.tuple[i] + this.tuple[i];
						}
				}
				return sum;
		}
		/** Returns an exact copy of this tuple. */
		public Tuple clone(){
				Tuple clone = new Tuple(this.tuple);
				for( int i = 0; i < this.length; i++){
					  clone.tuple[i] = this.tuple[i];
				}
				return clone;
		}

    /** Returns true if obj is a tuple which has the same length and elements, respectively, as this tuple */
		public boolean equals (Object obj){
			  if(obj == null){
					  return false;
				} else if(obj.getClass() == this.getClass()){
					  if(obj.toString().compareTo(this.toString()) == 0){
							  return true;
						} else {
							  return false;
						}
				} else {
					  return false;
				}
		}

    /** Returns the value of the ith element of this tuple (zero-based indexing) */
		public int getElement (int i) {
        if(i < this.length){
					  return this.tuple[i];
				} else {
					  throw new IllegalArgumentException("Must input an integer that is less than the length of the tuple");
				}
		}

    /** Sets the value of the ith element of this tuple (zero-based indexing) */
		public void setElement (int i, int value){
        if(i < this.length()){
					  this.tuple[i] = value;
				} else {
					  throw new IllegalArgumentException("Must input an integer that is less than the length of the tuple");
				}
		}

    /** Returns the number of elements in this tuple */
		public int length(){
				return this.length;
		}

    /** Returns the sum of the elements in this tuple */
		public int sum(){
        int sum = 0;
				for( int i = 0; i < this.length; i++ ) {
					  sum += this.tuple[i];
				}
				return sum;
		}

    /** Returns a stringy representation of this tuple, e.g., [2,-1,0,5] */
		public String toString(){
				return java.util.Arrays.toString(this.tuple);
		}

		/** Comprehensive set of tests */
		public static void main(String[] args) {
				int[] test = {0, 0, 0, 0, 0};
				Tuple t = new Tuple(5);
        Tuple tA = new Tuple(test);
				int[] test1 = {1,2,3,4,5};
				Tuple tester = new Tuple(test1);

				// 1 Test constructors and toString
				System.out.println("Tests for constructors and toString: ");
				System.out.println(tA.toString());
				System.out.println(t.toString());

				// Test equals
				System.out.println("Tests for equals: ");
				System.out.println(tA.equals(t));
				System.out.println(! tA.equals(tester));

				// Test makeTupleFromData
				System.out.println("Tests for makeTupleFromData: ");
				System.out.println(t.equals(makeTupleFromData(test)));
				System.out.println(makeTupleFromData(test).equals(t));
				System.out.println(tester.equals(makeTupleFromData(test1)));
				System.out.println(makeTupleFromData(test1).equals(tester));


				// Test add function
				System.out.println("Tests for add: ");
				System.out.println(t.add(tA).toString().equals("[0, 0, 0, 0, 0]"));
				System.out.println(t.add(tester).toString().equals("[1, 2, 3, 4, 5]"));
				System.out.println(tester.add(tA).toString().equals("[1, 2, 3, 4, 5]"));
				System.out.println(tA.add(t).toString().equals("[0, 0, 0, 0, 0]"));

				// Test clone function
				System.out.println("Tests for clone:");
				System.out.println(t.equals(t.clone()));
				System.out.println(tA.equals(tA.clone()));
				System.out.println(tester.equals(tester.clone()));
				System.out.println(! t.equals(tester.clone()));

				// Test getElement
				System.out.println("Tests for getElement:");
				System.out.println(t.getElement(2) == 0);
				System.out.println(tA.getElement(4) == 0);
				System.out.println(tester.getElement(0) == 1);

				// Test setElement
        System.out.println("Tests for setElement:");
				t.setElement(2,2);
				System.out.println(t.getElement(2) == 2);
				t.setElement(2,25);
				System.out.println(t.getElement(2) == 25);
				t.setElement(2,256);
				System.out.println(t.getElement(2) == 256);
				t.setElement(2,5);
				System.out.println(t.getElement(2) == 5);


				// Test sum
        System.out.println("Tests for sum:");
				System.out.println(t.sum() == 5);
				System.out.println(tA.sum() == 0);
				System.out.println(tester.sum() == 15);
				System.out.println(tester.sum() + t.sum() == 20);


		}




}
