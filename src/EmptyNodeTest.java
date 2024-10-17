import student.TestCase;

public class EmptyNodeTest extends student.TestCase
{


        private EmptyNode emptyNode;
        private Seminar seminar;
        private BoundingBox bbox;

        /**
         * Set up the test environment before each test.
         */
        public void setUp() {
            // Use the singleton pattern to get an EmptyNode instance
            emptyNode = EmptyNode.getInstance();

            // Set up a seminar and bounding box for testing
            seminar = new Seminar(
                1,
                "Test Seminar",
                "2023-10-10",
                90,
                (short)50,
                (short)50,
                150,
                new String[] {"Tech"},
                "A test seminar");

            bbox = new BoundingBox(0, 0, 100, 100); // Example bounding box
        }

        /**
         * Test the singleton pattern of EmptyNode.
         */
        public void testSingletonPattern() {
            // Ensure that only one instance of EmptyNode is created
            EmptyNode anotherInstance = EmptyNode.getInstance();
            assertSame(emptyNode, anotherInstance); // Both should refer to the same object
        }

        /**
         * Test the insert method in EmptyNode.
         */
        public void testInsert() {
            // Insert should return a new LeafNode since we are replacing the empty node
            BintreeNode result = emptyNode.insert(seminar, 0, bbox);
            assertNotNull(result);
            assertTrue(result instanceof LeafNode); // Ensure it returns a LeafNode
        }

        /**
         * Test the remove method in EmptyNode.
         */
        public void testRemove() {
            // Removing from an empty node should still return the EmptyNode instance
            BintreeNode result = emptyNode.remove(seminar, 0, bbox);
            assertNotNull(result);
            assertSame(emptyNode, result); // Should return the same EmptyNode instance
        }

        /**
         * Test the isInternal method.
         */
        public void testIsInternal() {
            assertFalse(emptyNode.isInternal()); // EmptyNode should never be internal
        }

        /**
         * Test the isLeaf method.
         */
        public void testIsLeaf() {
            assertFalse(emptyNode.isLeaf()); // EmptyNode should never be a leaf
        }
    }



