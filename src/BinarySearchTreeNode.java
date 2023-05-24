/**
 * Axel Sterner axst3162
 * Egil Österlind egae8586
 * Hanna Rudöfors haru3801
 *
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) Det är också den enda av klasserna ni
 * ska lämna in. Glöm inte att namn och användarnamn ska stå i en kommentar
 * högst upp, och att en eventuell paketdeklarationen måste plockas bort vid inlämningen för
 * att koden ska gå igenom de automatiska testerna.
 * <p>
 * De ändringar som är tillåtna är begränsade av följande:
 * <ul>
 * <li>Ni får INTE byta namn på klassen.
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans. Detta gäller också alterntiv
 * till loopar, så som strömmar.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * <li>Ni får INTE låta NÅGON metod ta en parameter av typen
 * BinarySearchTreeNode. Enbart den generiska typen (T eller vad ni väljer att
 * kalla den), String, StringBuilder, StringBuffer, samt primitiva typer är
 * tillåtna.
 * </ul>
 *
 * @param <T>
 * @author henrikbe
 */
//@SuppressWarnings("unused") // Denna rad ska plockas bort. Den finns här
// tillfälligt för att vi inte ska tro att det är
// fel i koden. Varningar ska normalt inte döljas på
// detta sätt, de är (oftast) fel som ska fixas.
public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;


    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {
        int compareToResult = data.compareTo(this.data); // -1,0,1.

        if (compareToResult < 0) {
            if (left == null) {
                left = new BinarySearchTreeNode<T>(data);
                return true;
            } else {
                return left.add(data);
            }
        }
        if (compareToResult > 0) {
            if (right == null) {
                right = new BinarySearchTreeNode<T>(data);
                return true;
            } else {
                return right.add(data);
            }
        }
        return false;
    }

    private T findMin() {
        if (left == null) {
            return this.data;
        }
        return left.findMin();
    }

    public BinarySearchTreeNode<T> remove(T data) { // SKICKA MED ROOT NODEN, INTE DET SOM TAS BORT.

        int compareResult = data.compareTo(this.data);

        if (compareResult == 0) {
            if (left == null && right == null) { // Är också ett löv.
                return null;
            }
            if (left != null && right == null) { // Ett barn till vänster
                return left;
            }
            if (left == null){ // Ett barn
                return right;
            }
            // Vi vet att det finns två barn.
            this.data = right.findMin();
            this.right = right.remove(this.data);

        } else if (left != null && compareResult < 0) {
            left = left.remove(data);
        } else if (right != null) {
            right = right.remove(data);
        }
        return this;
    }

    public boolean contains(T data) {
        int compareToResult = data.compareTo(this.data);
        if (compareToResult == 0) {
            return true;
        }
        // Kollar först så att datan inte är null
        if (right != null && compareToResult > 0) {
            return right.contains(data);
        }

        if (left != null && compareToResult < 0) {
            return left.contains(data);
        }
        return false;
    }

    public int size() {
        return (left == null ? 0 : left.size()) + 1 + (right == null ? 0 : right.size());
    }


    public int depth() {// Vänster led jämföra med högerled.
        int l = -1;
        int r = -1;
        if (left != null) {
            l = left.depth();
        }
        if (right != null) {
            r = right.depth();
        }
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    public String toString() {
        String result = "";
        if (left != null) {
            result = result + left + ", ";
        }
        result = result + data;

        if (right != null) {
            result = result + ", " + right;
        }
        return result;

    }
}
