// Generated from com\compilador\MiLenguaje.g4 by ANTLR 4.9.3
package com.compilador;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiLenguajeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PA=1, PC=2, CA=3, CC=4, LA=5, LC=6, PYC=7, COMA=8, IGUAL=9, MAYOR=10, 
		MAYOR_IGUAL=11, MENOR=12, MENOR_IGUAL=13, EQL=14, DISTINTO=15, SUM=16, 
		RES=17, MUL=18, DIV=19, MOD=20, OR=21, AND=22, NOT=23, FOR=24, WHILE=25, 
		IF=26, ELSE=27, BREAK=28, CONTINUE=29, INT=30, CHAR=31, DOUBLE=32, VOID=33, 
		BOOL=34, STRING=35, FLOAT=36, RETURN=37, ID=38, INTEGER=39, DECIMAL=40, 
		CHARACTER=41, COMENTARIO_LINEA=42, COMENTARIO_BLOQUE=43, WS=44, OTRO=45;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PA", "PC", "CA", "CC", "LA", "LC", "PYC", "COMA", "IGUAL", "MAYOR", 
			"MAYOR_IGUAL", "MENOR", "MENOR_IGUAL", "EQL", "DISTINTO", "SUM", "RES", 
			"MUL", "DIV", "MOD", "OR", "AND", "NOT", "FOR", "WHILE", "IF", "ELSE", 
			"BREAK", "CONTINUE", "INT", "CHAR", "DOUBLE", "VOID", "BOOL", "STRING", 
			"FLOAT", "RETURN", "ID", "INTEGER", "DECIMAL", "CHARACTER", "COMENTARIO_LINEA", 
			"COMENTARIO_BLOQUE", "WS", "OTRO", "LETRA", "DIGITO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'{'", "'}'", "';'", "','", "'='", 
			"'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'||'", "'&&'", "'!'", "'for'", "'while'", "'if'", "'else'", "'break'", 
			"'continue'", "'int'", "'char'", "'double'", "'void'", "'bool'", "'string'", 
			"'float'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PA", "PC", "CA", "CC", "LA", "LC", "PYC", "COMA", "IGUAL", "MAYOR", 
			"MAYOR_IGUAL", "MENOR", "MENOR_IGUAL", "EQL", "DISTINTO", "SUM", "RES", 
			"MUL", "DIV", "MOD", "OR", "AND", "NOT", "FOR", "WHILE", "IF", "ELSE", 
			"BREAK", "CONTINUE", "INT", "CHAR", "DOUBLE", "VOID", "BOOL", "STRING", 
			"FLOAT", "RETURN", "ID", "INTEGER", "DECIMAL", "CHARACTER", "COMENTARIO_LINEA", 
			"COMENTARIO_BLOQUE", "WS", "OTRO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MiLenguajeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiLenguaje.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2/\u0124\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3"+
		"!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%"+
		"\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\5\'\u00e7\n\'\3\'\3\'\3\'\7\'\u00ec"+
		"\n\'\f\'\16\'\u00ef\13\'\3(\6(\u00f2\n(\r(\16(\u00f3\3)\3)\3)\3)\3*\3"+
		"*\3*\3*\5*\u00fe\n*\3*\3*\3+\3+\3+\3+\7+\u0106\n+\f+\16+\u0109\13+\3+"+
		"\3+\3,\3,\3,\3,\7,\u0111\n,\f,\16,\u0114\13,\3,\3,\3,\3,\3,\3-\3-\3-\3"+
		"-\3.\3.\3/\3/\3\60\3\60\3\u0112\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\2_\2\3\2\7\5\2\f\f\17\17))\4\2\f\f\17\17\5\2\13\f\17\17\"\"\4\2C\\"+
		"c|\3\2\62;\2\u0129\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3a\3\2\2\2"+
		"\5c\3\2\2\2\7e\3\2\2\2\tg\3\2\2\2\13i\3\2\2\2\rk\3\2\2\2\17m\3\2\2\2\21"+
		"o\3\2\2\2\23q\3\2\2\2\25s\3\2\2\2\27u\3\2\2\2\31x\3\2\2\2\33z\3\2\2\2"+
		"\35}\3\2\2\2\37\u0080\3\2\2\2!\u0083\3\2\2\2#\u0085\3\2\2\2%\u0087\3\2"+
		"\2\2\'\u0089\3\2\2\2)\u008b\3\2\2\2+\u008d\3\2\2\2-\u0090\3\2\2\2/\u0093"+
		"\3\2\2\2\61\u0095\3\2\2\2\63\u0099\3\2\2\2\65\u009f\3\2\2\2\67\u00a2\3"+
		"\2\2\29\u00a7\3\2\2\2;\u00ad\3\2\2\2=\u00b6\3\2\2\2?\u00ba\3\2\2\2A\u00bf"+
		"\3\2\2\2C\u00c6\3\2\2\2E\u00cb\3\2\2\2G\u00d0\3\2\2\2I\u00d7\3\2\2\2K"+
		"\u00dd\3\2\2\2M\u00e6\3\2\2\2O\u00f1\3\2\2\2Q\u00f5\3\2\2\2S\u00f9\3\2"+
		"\2\2U\u0101\3\2\2\2W\u010c\3\2\2\2Y\u011a\3\2\2\2[\u011e\3\2\2\2]\u0120"+
		"\3\2\2\2_\u0122\3\2\2\2ab\7*\2\2b\4\3\2\2\2cd\7+\2\2d\6\3\2\2\2ef\7]\2"+
		"\2f\b\3\2\2\2gh\7_\2\2h\n\3\2\2\2ij\7}\2\2j\f\3\2\2\2kl\7\177\2\2l\16"+
		"\3\2\2\2mn\7=\2\2n\20\3\2\2\2op\7.\2\2p\22\3\2\2\2qr\7?\2\2r\24\3\2\2"+
		"\2st\7@\2\2t\26\3\2\2\2uv\7@\2\2vw\7?\2\2w\30\3\2\2\2xy\7>\2\2y\32\3\2"+
		"\2\2z{\7>\2\2{|\7?\2\2|\34\3\2\2\2}~\7?\2\2~\177\7?\2\2\177\36\3\2\2\2"+
		"\u0080\u0081\7#\2\2\u0081\u0082\7?\2\2\u0082 \3\2\2\2\u0083\u0084\7-\2"+
		"\2\u0084\"\3\2\2\2\u0085\u0086\7/\2\2\u0086$\3\2\2\2\u0087\u0088\7,\2"+
		"\2\u0088&\3\2\2\2\u0089\u008a\7\61\2\2\u008a(\3\2\2\2\u008b\u008c\7\'"+
		"\2\2\u008c*\3\2\2\2\u008d\u008e\7~\2\2\u008e\u008f\7~\2\2\u008f,\3\2\2"+
		"\2\u0090\u0091\7(\2\2\u0091\u0092\7(\2\2\u0092.\3\2\2\2\u0093\u0094\7"+
		"#\2\2\u0094\60\3\2\2\2\u0095\u0096\7h\2\2\u0096\u0097\7q\2\2\u0097\u0098"+
		"\7t\2\2\u0098\62\3\2\2\2\u0099\u009a\7y\2\2\u009a\u009b\7j\2\2\u009b\u009c"+
		"\7k\2\2\u009c\u009d\7n\2\2\u009d\u009e\7g\2\2\u009e\64\3\2\2\2\u009f\u00a0"+
		"\7k\2\2\u00a0\u00a1\7h\2\2\u00a1\66\3\2\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4"+
		"\7n\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6\7g\2\2\u00a68\3\2\2\2\u00a7\u00a8"+
		"\7d\2\2\u00a8\u00a9\7t\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7c\2\2\u00ab"+
		"\u00ac\7m\2\2\u00ac:\3\2\2\2\u00ad\u00ae\7e\2\2\u00ae\u00af\7q\2\2\u00af"+
		"\u00b0\7p\2\2\u00b0\u00b1\7v\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7p\2\2"+
		"\u00b3\u00b4\7w\2\2\u00b4\u00b5\7g\2\2\u00b5<\3\2\2\2\u00b6\u00b7\7k\2"+
		"\2\u00b7\u00b8\7p\2\2\u00b8\u00b9\7v\2\2\u00b9>\3\2\2\2\u00ba\u00bb\7"+
		"e\2\2\u00bb\u00bc\7j\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7t\2\2\u00be@"+
		"\3\2\2\2\u00bf\u00c0\7f\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7w\2\2\u00c2"+
		"\u00c3\7d\2\2\u00c3\u00c4\7n\2\2\u00c4\u00c5\7g\2\2\u00c5B\3\2\2\2\u00c6"+
		"\u00c7\7x\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7f\2\2"+
		"\u00caD\3\2\2\2\u00cb\u00cc\7d\2\2\u00cc\u00cd\7q\2\2\u00cd\u00ce\7q\2"+
		"\2\u00ce\u00cf\7n\2\2\u00cfF\3\2\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7"+
		"v\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6"+
		"\7i\2\2\u00d6H\3\2\2\2\u00d7\u00d8\7h\2\2\u00d8\u00d9\7n\2\2\u00d9\u00da"+
		"\7q\2\2\u00da\u00db\7c\2\2\u00db\u00dc\7v\2\2\u00dcJ\3\2\2\2\u00dd\u00de"+
		"\7t\2\2\u00de\u00df\7g\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1\7w\2\2\u00e1"+
		"\u00e2\7t\2\2\u00e2\u00e3\7p\2\2\u00e3L\3\2\2\2\u00e4\u00e7\5]/\2\u00e5"+
		"\u00e7\7a\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ed\3\2"+
		"\2\2\u00e8\u00ec\5]/\2\u00e9\u00ec\5_\60\2\u00ea\u00ec\7a\2\2\u00eb\u00e8"+
		"\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed"+
		"\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00eeN\3\2\2\2\u00ef\u00ed\3\2\2\2"+
		"\u00f0\u00f2\5_\60\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1"+
		"\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4P\3\2\2\2\u00f5\u00f6\5O(\2\u00f6\u00f7"+
		"\7\60\2\2\u00f7\u00f8\5O(\2\u00f8R\3\2\2\2\u00f9\u00fd\7)\2\2\u00fa\u00fe"+
		"\n\2\2\2\u00fb\u00fc\7^\2\2\u00fc\u00fe\13\2\2\2\u00fd\u00fa\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\7)\2\2\u0100T\3\2\2\2\u0101"+
		"\u0102\7\61\2\2\u0102\u0103\7\61\2\2\u0103\u0107\3\2\2\2\u0104\u0106\n"+
		"\3\2\2\u0105\u0104\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\b+"+
		"\2\2\u010bV\3\2\2\2\u010c\u010d\7\61\2\2\u010d\u010e\7,\2\2\u010e\u0112"+
		"\3\2\2\2\u010f\u0111\13\2\2\2\u0110\u010f\3\2\2\2\u0111\u0114\3\2\2\2"+
		"\u0112\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0115\3\2\2\2\u0114\u0112"+
		"\3\2\2\2\u0115\u0116\7,\2\2\u0116\u0117\7\61\2\2\u0117\u0118\3\2\2\2\u0118"+
		"\u0119\b,\2\2\u0119X\3\2\2\2\u011a\u011b\t\4\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u011d\b-\2\2\u011dZ\3\2\2\2\u011e\u011f\13\2\2\2\u011f\\\3\2\2\2\u0120"+
		"\u0121\t\5\2\2\u0121^\3\2\2\2\u0122\u0123\t\6\2\2\u0123`\3\2\2\2\n\2\u00e6"+
		"\u00eb\u00ed\u00f3\u00fd\u0107\u0112\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}