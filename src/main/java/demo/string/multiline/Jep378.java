package demo.string.multiline;

public class Jep378 {

	public static final String HTML = """
		<html>
			<head>
				 <meta charset="UTF-8">
			</head>
			<body>
				<div style="color:red;">Greetings</div>
			</body>
		</html>
		""";

	public static final String SQL = """
		begin transaction;
		select * from company where name like "*acme*";
		commit;
		""";
	
}
