import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {


		ArrayList<File> files=new ArrayList<>();
		try {
			getFiles("/storage/emulated/0/AppProjects/dafeiji/SSTTGG/app/src/main/java/cs/", files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (File javaFile:files) {


			String fs = readString(javaFile);


			LinkedList<String> strline=new LinkedList<>();
			Collections.addAll(strline, fs.split("\\n"));

			ListIterator<String> iterator = strline.listIterator();
			while (iterator.hasNext()) {
				String s=iterator.next();
				if (s.indexOf("using") < 2 && s.indexOf("using") >= 0) {
					iterator.remove();
					continue;
				}
				if (s.trim().equals("")) {
					iterator.remove();
					continue;
				}
				if (s.trim().indexOf("namespace") < 2 && s.trim().indexOf("namespace") >= 0) {
					iterator.remove();
					continue;
				}
				iterator.set(getToReplace(s));
			}
			strline.remove(strline.size() - 1);
			if (strline.get(0).contains(":")) {
				strline.set(0, strline.get(0).replace(":", " extends "));
			}
			saveString(new File(javaFile.getAbsolutePath() + ""), print(strline));

		}
		System.out.println("ok at " + new Date());

	}

	private static String print(LinkedList<String> strline) {
		StringBuilder sb = new StringBuilder();
		for (String s:strline) {
			sb.append(s).append("\n");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}

	public static String getToReplace(String s) {
		HashMap<String,String> toReplace=new HashMap<String,String>(){{
			/*	put(".Contains", ".contains");
				put(" const ", " final ");
				put(" bool ", " boolean ");
				put("string", "String");
				put("Math.Sin", "Math.sin");
				put("Math.Cos", "Math.cos");
				put("Math.Abs", "Math.abs");
				put("Math.Sqrt", "Math.sqrt");
				put("float.Parse", "Float.parseFloat");
				put("double.parse", "Double.parseDouble");
				put("int.Parse", "Integer.parseInt");
				put(".Count", ".size()");
				put(" List", " ArrayList");
				put("@event", "_event");
				put("==\"", ".equals(\"");
				put("Hashtable", "HashMap<String,Integer>");
				put("new HashMap();", "new HashMap<>();");
				put("Split", "split");
				put("'", "\"");
				put("\".ToCharArray()", "");
				put(".Clear()", ".clear()");
				put("position.X", "position.x");
				put("position.Y", "position.y");
				put("PointF", "Vector2");
				put("Add", "put");
				put("foreach", "for");
				put(" in ", " : ");
				put("byte.MaxValue", "Byte.MAX_VALUE");
				put("Contains", "contains");
				put("Rectangle?", "Rectangle");
				put("RemoveAt", "remove");
				put("Atan", "atan");
				put("[this.parentid]", ".get(this.parentid)");
				put("[this.id]", ".get(this.id)");*/
				put("<int>","<Integer>");

			}};

		String ret = s;
		for (Map.Entry<String,String> entry:toReplace.entrySet()) {
			ret = ret.replace(entry.getKey(), entry.getValue());
		}
		return ret;
	}

	public static void getFiles(String path, ArrayList<File> list) throws Exception {
        //目标集合fileArrayList
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath(), list);
                } else {
					if (fileIndex.getName().endsWith(".java")) {
						list.add(fileIndex);
					}
                }
            }
        }
    }

	public static void saveString(File f, String str) {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			writer.write(str);
			writer.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public static String readString(File f) {
		String s = null;
		try {      
			long filelength = f.length();
			byte[] filecontent = new byte[(int) filelength];
			FileInputStream in = new FileInputStream(f);
			in.read(filecontent);
			in.close();
			s = new String(filecontent, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;	
	}


//	public static String replaceStringEquals(String origin){
//		String reg=".{1,}\\s{0,}==(.{1,})";
//		String ts;
//		Matcher m2 = Pattern.compile(reg).matcher(origin);  
//		if (m2.find()) {
//			m2.replaceAll("jj");
//			ts = m2.group(1); 
//		}
//		
//		return null;
//	}
}
