import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.Rule;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class testCSVparser {
    public Laba2 instance;

    @Rule
    public TemporaryFolder helpFolder = new TemporaryFolder();

    public testCSVparser() {
        this.instance = new Laba2();
    }

    @Test
    public void testString() {
        assertEquals("1+1+1", this.instance.checkStr("a,b,C"));
        assertEquals("2+2+11", this.instance.checkStr("\"a\",\"bc\",\"def\"\"ggggg\""));
        assertEquals("1+3+1", this.instance.checkStr("a,\"b,c\",d"));
        assertEquals("2+2+0+11", this.instance.checkStr("ab,cd,\"\",efg\"\"rrrrrr"));
        assertEquals("2+2+0+5", this.instance.checkStr("\"ab\",\"cd\",\"\",\"56789\""));
        assertEquals("2+2+13+14", this.instance.checkStr("\"ab\",\"cd\",\"e\"\"fg\"\"111111\"," + "\"a\"\"bc,\"\"defgi\""));
    }

    @Test
    public void fileTest() throws IOException {   //// change name
        File file1 = helpFolder.newFile("InTestFileCVS.cvs");
        BufferedWriter b = new BufferedWriter(new FileWriter(file1));
        b.write("a,\"b,\",c");
        b.close();

        File file2 = helpFolder.newFile("OutTestFileCVS.cvs");
        this.instance.checkFile(file1.getAbsolutePath(), file2.getAbsolutePath());

        BufferedReader br = new BufferedReader(new FileReader(file2));
        assertEquals("1+2+1", br.readLine());
        br.close();
    }

    @Test
    public void gettersTest() {
        Laba2 testparser = new Laba2("a", "b");
        assertEquals("a", testparser.getDelIn());
        assertEquals("b", testparser.getDelOut());

    }
}
