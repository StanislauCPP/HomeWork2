package containers;

/**Node with only forward way*/
class MyNode<V> {
	public V value;
	public MyNode<V> next;

	public MyNode(V value) {
		this.value = value;
	}
}
