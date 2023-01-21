/* Das Java-Praktikum, dpunkt Verlag 2008, ISBN 978-3-89864-513-3
 * Aufgabe: Flaggen
 * Entwickelt mit: Sun Java 6 SE Development Kit
 */
package flag;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * Definiert Klasse zur Darstellung einer Pixelgrafik.
 *
 * @author Klaus Köhler, koehler@hm.edu
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 15.06.2008
 */
public class FlagRender extends Frame {
    private static final long serialVersionUID = 1L;

    FlagRender() {
        super();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                setVisible(false);

                insets = getInsets();
                setSize(img.getWidth() + insets.left + insets.right, img.getHeight() + insets.bottom + insets.top);
                setVisible(true);

                repaint();
            }

        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                System.exit(0);
            }

        });

        setVisible(false);
    }

    void load(final BufferedImage img, final String title) {
        this.img = img;

        setTitle(title);

        if(!isVisible()) {
            if(insets == null) {
                setVisible(true);
                insets = getInsets();
                setVisible(false);
            }
            setSize(img.getWidth() + insets.left + insets.right, img.getHeight() + insets.bottom + insets.top);
            setVisible(true);
        }

        repaint();
    }

    @Override
    public void paint(final Graphics g) {
        g.drawImage(img, insets.left, insets.top, this);
    }

    @Override
    public void update(final Graphics g) {
        g.drawImage(img, insets.left, insets.top, this);
    }

    private Insets insets = null;

    private BufferedImage img;

    /**
     * Grafische Darstellung eines Bildes,
     * dessen Pixel von einem anderen Programm geliefert werden.
     * Verwendet Refection.
     * main-Methode des aufgerufenen Programms muss folgende Parameterliste haben:
     *  Breite, Höhe, x-, y-Koordinate
     * Aufrufbeispiel: java flag/FlagRender flag.FlagDE 400 300
     * @param args Pfad.Klasse des aufgerufenen Programms; Breite und Höhe des Bildes
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void main(final String[] args) throws ClassNotFoundException, NoSuchMethodException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final int width = Integer.parseInt(args[1]);
        final int height = Integer.parseInt(args[2]);
        @SuppressWarnings("unchecked")
        final Class clazz = Class.forName(args[0]);
        @SuppressWarnings("unchecked")
        final Method main = clazz.getDeclaredMethod("main", String[].class);

        final Map<String, Integer> colors = new TreeMap<String, Integer>();
        final BufferedReader input = new BufferedReader(new StringReader("240 248 255  aliceblue\n250 235 215  antiquewhite\n255 239 219  antiquewhite1\n238 223 204  antiquewhite2\n205 192 176  antiquewhite3\n139 131 120  antiquewhite4\n127 255 212  aquamarine\n127 255 212  aquamarine1\n118 238 198  aquamarine2\n102 205 170  aquamarine3\n 69 139 116  aquamarine4\n240 255 255  azure\n240 255 255  azure1\n224 238 238  azure2\n193 205 205  azure3\n131 139 139  azure4\n245 245 220  beige\n255 228 196  bisque\n255 228 196  bisque1\n238 213 183  bisque2\n205 183 158  bisque3\n139 125 107  bisque4\n  0   0   0  black\n255 235 205  blanchedalmond\n  0   0 255  blue\n  0   0 255  blue1\n  0   0 238  blue2\n  0   0 205  blue3\n  0   0 139  blue4\n138  43 226  blueviolet\n165  42  42  brown\n255  64  64  brown1\n238  59  59  brown2\n205  51  51  brown3\n139  35  35  brown4\n222 184 135  burlywood\n255 211 155  burlywood1\n238 197 145  burlywood2\n205 170 125  burlywood3\n139 115  85  burlywood4\n 95 158 160  cadetblue\n152 245 255  cadetblue1\n142 229 238  cadetblue2\n122 197 205  cadetblue3\n 83 134 139  cadetblue4\n127 255   0  chartreuse\n127 255   0  chartreuse1\n118 238   0  chartreuse2\n102 205   0  chartreuse3\n 69 139   0  chartreuse4\n210 105  30  chocolate\n255 127  36  chocolate1\n238 118  33  chocolate2\n205 102  29  chocolate3\n139  69  19  chocolate4\n255 127  80  coral\n255 114  86  coral1\n238 106  80  coral2\n205  91  69  coral3\n139  62  47  coral4\n100 149 237  cornflowerblue\n255 248 220  cornsilk\n255 248 220  cornsilk1\n238 232 205  cornsilk2\n205 200 177  cornsilk3\n139 136 120  cornsilk4\n  0 255 255  cyan\n  0 255 255  cyan1\n  0 238 238  cyan2\n  0 205 205  cyan3\n  0 139 139  cyan4\n0     0 139  darkblue\n0   139 139  darkcyan\n184 134  11  darkgoldenrod\n255 185  15  darkgoldenrod1\n238 173  14  darkgoldenrod2\n205 149  12  darkgoldenrod3\n139 101   8  darkgoldenrod4\n169 169 169  darkgray\n  0 100   0  darkgreen\n169 169 169  darkgrey\n189 183 107  darkkhaki\n139   0 139  darkmagenta\n 85 107  47  darkolivegreen\n202 255 112  darkolivegreen1\n188 238 104  darkolivegreen2\n162 205  90  darkolivegreen3\n110 139  61  darkolivegreen4\n255 140   0  darkorange\n255 127   0  darkorange1\n238 118   0  darkorange2\n205 102   0  darkorange3\n139  69   0  darkorange4\n153  50 204  darkorchid\n191  62 255  darkorchid1\n178  58 238  darkorchid2\n154  50 205  darkorchid3\n104  34 139  darkorchid4\n139   0   0  darkred\n233 150 122  darksalmon\n143 188 143  darkseagreen\n193 255 193  darkseagreen1\n180 238 180  darkseagreen2\n155 205 155  darkseagreen3\n105 139 105  darkseagreen4\n 72  61 139  darkslateblue\n 47  79  79  darkslategray\n151 255 255  darkslategray1\n141 238 238  darkslategray2\n121 205 205  darkslategray3\n 82 139 139  darkslategray4\n 47  79  79  darkslategrey\n  0 206 209  darkturquoise\n148   0 211  darkviolet\n255  20 147  deeppink\n255  20 147  deeppink1\n238  18 137  deeppink2\n205  16 118  deeppink3\n139  10  80  deeppink4\n  0 191 255  deepskyblue\n  0 191 255  deepskyblue1\n  0 178 238  deepskyblue2\n  0 154 205  deepskyblue3\n  0 104 139  deepskyblue4\n105 105 105  dimgray\n105 105 105  dimgrey\n 30 144 255  dodgerblue\n 30 144 255  dodgerblue1\n 28 134 238  dodgerblue2\n 24 116 205  dodgerblue3\n 16  78 139  dodgerblue4\n178  34  34  firebrick\n255  48  48  firebrick1\n238  44  44  firebrick2\n205  38  38  firebrick3\n139  26  26  firebrick4\n255 250 240  floralwhite\n 34 139  34  forestgreen\n220 220 220  gainsboro\n248 248 255  ghostwhite\n255 215   0  gold\n255 215   0  gold1\n238 201   0  gold2\n205 173   0  gold3\n139 117   0  gold4\n218 165  32  goldenrod\n255 193  37  goldenrod1\n238 180  34  goldenrod2\n205 155  29  goldenrod3\n139 105  20  goldenrod4\n190 190 190  gray\n  0   0   0  gray0\n  3   3   3  gray1\n 26  26  26  gray10\n255 255 255  gray100\n 28  28  28  gray11\n 31  31  31  gray12\n 33  33  33  gray13\n 36  36  36  gray14\n 38  38  38  gray15\n 41  41  41  gray16\n 43  43  43  gray17\n 46  46  46  gray18\n 48  48  48  gray19\n  5   5   5  gray2\n 51  51  51  gray20\n 54  54  54  gray21\n 56  56  56  gray22\n 59  59  59  gray23\n 61  61  61  gray24\n 64  64  64  gray25\n 66  66  66  gray26\n 69  69  69  gray27\n 71  71  71  gray28\n 74  74  74  gray29\n  8   8   8  gray3\n 77  77  77  gray30\n 79  79  79  gray31\n 82  82  82  gray32\n 84  84  84  gray33\n 87  87  87  gray34\n 89  89  89  gray35\n 92  92  92  gray36\n 94  94  94  gray37\n 97  97  97  gray38\n 99  99  99  gray39\n 10  10  10  gray4\n102 102 102  gray40\n105 105 105  gray41\n107 107 107  gray42\n110 110 110  gray43\n112 112 112  gray44\n115 115 115  gray45\n117 117 117  gray46\n120 120 120  gray47\n122 122 122  gray48\n125 125 125  gray49\n 13  13  13  gray5\n127 127 127  gray50\n130 130 130  gray51\n133 133 133  gray52\n135 135 135  gray53\n138 138 138  gray54\n140 140 140  gray55\n143 143 143  gray56\n145 145 145  gray57\n148 148 148  gray58\n150 150 150  gray59\n 15  15  15  gray6\n153 153 153  gray60\n156 156 156  gray61\n158 158 158  gray62\n161 161 161  gray63\n163 163 163  gray64\n166 166 166  gray65\n168 168 168  gray66\n171 171 171  gray67\n173 173 173  gray68\n176 176 176  gray69\n 18  18  18  gray7\n179 179 179  gray70\n181 181 181  gray71\n184 184 184  gray72\n186 186 186  gray73\n189 189 189  gray74\n191 191 191  gray75\n194 194 194  gray76\n196 196 196  gray77\n199 199 199  gray78\n201 201 201  gray79\n 20  20  20  gray8\n204 204 204  gray80\n207 207 207  gray81\n209 209 209  gray82\n212 212 212  gray83\n214 214 214  gray84\n217 217 217  gray85\n219 219 219  gray86\n222 222 222  gray87\n224 224 224  gray88\n227 227 227  gray89\n 23  23  23  gray9\n229 229 229  gray90\n232 232 232  gray91\n235 235 235  gray92\n237 237 237  gray93\n240 240 240  gray94\n242 242 242  gray95\n245 245 245  gray96\n247 247 247  gray97\n250 250 250  gray98\n252 252 252  gray99\n  0 255   0  green\n  0 255   0  green1\n  0 238   0  green2\n  0 205   0  green3\n  0 139   0  green4\n173 255  47  greenyellow\n190 190 190  grey\n  0   0   0  grey0\n  3   3   3  grey1\n 26  26  26  grey10\n255 255 255  grey100\n 28  28  28  grey11\n 31  31  31  grey12\n 33  33  33  grey13\n 36  36  36  grey14\n 38  38  38  grey15\n 41  41  41  grey16\n 43  43  43  grey17\n 46  46  46  grey18\n 48  48  48  grey19\n  5   5   5  grey2\n 51  51  51  grey20\n 54  54  54  grey21\n 56  56  56  grey22\n 59  59  59  grey23\n 61  61  61  grey24\n 64  64  64  grey25\n 66  66  66  grey26\n 69  69  69  grey27\n 71  71  71  grey28\n 74  74  74  grey29\n  8   8   8  grey3\n 77  77  77  grey30\n 79  79  79  grey31\n 82  82  82  grey32\n 84  84  84  grey33\n 87  87  87  grey34\n 89  89  89  grey35\n 92  92  92  grey36\n 94  94  94  grey37\n 97  97  97  grey38\n 99  99  99  grey39\n 10  10  10  grey4\n102 102 102  grey40\n105 105 105  grey41\n107 107 107  grey42\n110 110 110  grey43\n112 112 112  grey44\n115 115 115  grey45\n117 117 117  grey46\n120 120 120  grey47\n122 122 122  grey48\n125 125 125  grey49\n 13  13  13  grey5\n127 127 127  grey50\n130 130 130  grey51\n133 133 133  grey52\n135 135 135  grey53\n138 138 138  grey54\n140 140 140  grey55\n143 143 143  grey56\n145 145 145  grey57\n148 148 148  grey58\n150 150 150  grey59\n 15  15  15  grey6\n153 153 153  grey60\n156 156 156  grey61\n158 158 158  grey62\n161 161 161  grey63\n163 163 163  grey64\n166 166 166  grey65\n168 168 168  grey66\n171 171 171  grey67\n173 173 173  grey68\n176 176 176  grey69\n 18  18  18  grey7\n179 179 179  grey70\n181 181 181  grey71\n184 184 184  grey72\n186 186 186  grey73\n189 189 189  grey74\n191 191 191  grey75\n194 194 194  grey76\n196 196 196  grey77\n199 199 199  grey78\n201 201 201  grey79\n 20  20  20  grey8\n204 204 204  grey80\n207 207 207  grey81\n209 209 209  grey82\n212 212 212  grey83\n214 214 214  grey84\n217 217 217  grey85\n219 219 219  grey86\n222 222 222  grey87\n224 224 224  grey88\n227 227 227  grey89\n 23  23  23  grey9\n229 229 229  grey90\n232 232 232  grey91\n235 235 235  grey92\n237 237 237  grey93\n240 240 240  grey94\n242 242 242  grey95\n245 245 245  grey96\n247 247 247  grey97\n250 250 250  grey98\n252 252 252  grey99\n240 255 240  honeydew\n240 255 240  honeydew1\n224 238 224  honeydew2\n193 205 193  honeydew3\n131 139 131  honeydew4\n255 105 180  hotpink\n255 110 180  hotpink1\n238 106 167  hotpink2\n205  96 144  hotpink3\n139  58  98  hotpink4\n205  92  92  indianred\n255 106 106  indianred1\n238  99  99  indianred2\n205  85  85  indianred3\n139  58  58  indianred4\n255 255 240  ivory\n255 255 240  ivory1\n238 238 224  ivory2\n205 205 193  ivory3\n139 139 131  ivory4\n240 230 140  khaki\n255 246 143  khaki1\n238 230 133  khaki2\n205 198 115  khaki3\n139 134  78  khaki4\n230 230 250  lavender\n255 240 245  lavenderblush\n255 240 245  lavenderblush1\n238 224 229  lavenderblush2\n205 193 197  lavenderblush3\n139 131 134  lavenderblush4\n124 252   0  lawngreen\n255 250 205  lemonchiffon\n255 250 205  lemonchiffon1\n238 233 191  lemonchiffon2\n205 201 165  lemonchiffon3\n139 137 112  lemonchiffon4\n173 216 230  lightblue\n191 239 255  lightblue1\n178 223 238  lightblue2\n154 192 205  lightblue3\n104 131 139  lightblue4\n240 128 128  lightcoral\n224 255 255  lightcyan\n224 255 255  lightcyan1\n209 238 238  lightcyan2\n180 205 205  lightcyan3\n122 139 139  lightcyan4\n238 221 130  lightgoldenrod\n255 236 139  lightgoldenrod1\n238 220 130  lightgoldenrod2\n205 190 112  lightgoldenrod3\n139 129  76  lightgoldenrod4\n250 250 210  lightgoldenrodyellow\n211 211 211  lightgray\n144 238 144  lightgreen\n211 211 211  lightgrey\n255 182 193  lightpink\n255 174 185  lightpink1\n238 162 173  lightpink2\n205 140 149  lightpink3\n139  95 101  lightpink4\n255 160 122  lightsalmon\n255 160 122  lightsalmon1\n238 149 114  lightsalmon2\n205 129  98  lightsalmon3\n139  87  66  lightsalmon4\n 32 178 170  lightseagreen\n135 206 250  lightskyblue\n176 226 255  lightskyblue1\n164 211 238  lightskyblue2\n141 182 205  lightskyblue3\n 96 123 139  lightskyblue4\n132 112 255  lightslateblue\n119 136 153  lightslategray\n119 136 153  lightslategrey\n176 196 222  lightsteelblue\n202 225 255  lightsteelblue1\n188 210 238  lightsteelblue2\n162 181 205  lightsteelblue3\n110 123 139  lightsteelblue4\n255 255 224  lightyellow\n255 255 224  lightyellow1\n238 238 209  lightyellow2\n205 205 180  lightyellow3\n139 139 122  lightyellow4\n 50 205  50  limegreen\n250 240 230  linen\n255   0 255  magenta\n255   0 255  magenta1\n238   0 238  magenta2\n205   0 205  magenta3\n139   0 139  magenta4\n176  48  96  maroon\n255  52 179  maroon1\n238  48 167  maroon2\n205  41 144  maroon3\n139  28  98  maroon4\n102 205 170  mediumaquamarine\n  0   0 205  mediumblue\n186  85 211  mediumorchid\n224 102 255  mediumorchid1\n209  95 238  mediumorchid2\n180  82 205  mediumorchid3\n122  55 139  mediumorchid4\n147 112 219  mediumpurple\n171 130 255  mediumpurple1\n159 121 238  mediumpurple2\n137 104 205  mediumpurple3\n 93  71 139  mediumpurple4\n 60 179 113  mediumseagreen\n123 104 238  mediumslateblue\n  0 250 154  mediumspringgreen\n 72 209 204  mediumturquoise\n199  21 133  mediumvioletred\n 25  25 112  midnightblue\n245 255 250  mintcream\n255 228 225  mistyrose\n255 228 225  mistyrose1\n238 213 210  mistyrose2\n205 183 181  mistyrose3\n139 125 123  mistyrose4\n255 228 181  moccasin\n255 222 173  navajowhite\n255 222 173  navajowhite1\n238 207 161  navajowhite2\n205 179 139  navajowhite3\n139 121  94  navajowhite4\n  0   0 128  navy\n  0   0 128  navyblue\n253 245 230  oldlace\n107 142  35  olivedrab\n192 255  62  olivedrab1\n179 238  58  olivedrab2\n154 205  50  olivedrab3\n105 139  34  olivedrab4\n255 165   0  orange\n255 165   0  orange1\n238 154   0  orange2\n205 133   0  orange3\n139  90   0  orange4\n255  69   0  orangered\n255  69   0  orangered1\n238  64   0  orangered2\n205  55   0  orangered3\n139  37   0  orangered4\n218 112 214  orchid\n255 131 250  orchid1\n238 122 233  orchid2\n205 105 201  orchid3\n139  71 137  orchid4\n238 232 170  palegoldenrod\n152 251 152  palegreen\n154 255 154  palegreen1\n144 238 144  palegreen2\n124 205 124  palegreen3\n 84 139  84  palegreen4\n175 238 238  paleturquoise\n187 255 255  paleturquoise1\n174 238 238  paleturquoise2\n150 205 205  paleturquoise3\n102 139 139  paleturquoise4\n219 112 147  palevioletred\n255 130 171  palevioletred1\n238 121 159  palevioletred2\n205 104 137  palevioletred3\n139  71  93  palevioletred4\n255 239 213  papayawhip\n255 218 185  peachpuff\n255 218 185  peachpuff1\n238 203 173  peachpuff2\n205 175 149  peachpuff3\n139 119 101  peachpuff4\n205 133  63  peru\n255 192 203  pink\n255 181 197  pink1\n238 169 184  pink2\n205 145 158  pink3\n139  99 108  pink4\n221 160 221  plum\n255 187 255  plum1\n238 174 238  plum2\n205 150 205  plum3\n139 102 139  plum4\n176 224 230  powderblue\n160  32 240  purple\n155  48 255  purple1\n145  44 238  purple2\n125  38 205  purple3\n 85  26 139  purple4\n255   0   0  red\n255   0   0  red1\n238   0   0  red2\n205   0   0  red3\n139   0   0  red4\n188 143 143  rosybrown\n255 193 193  rosybrown1\n238 180 180  rosybrown2\n205 155 155  rosybrown3\n139 105 105  rosybrown4\n 65 105 225  royalblue\n 72 118 255  royalblue1\n 67 110 238  royalblue2\n 58  95 205  royalblue3\n 39  64 139  royalblue4\n139  69  19  saddlebrown\n250 128 114  salmon\n255 140 105  salmon1\n238 130  98  salmon2\n205 112  84  salmon3\n139  76  57  salmon4\n244 164  96  sandybrown\n 46 139  87  seagreen\n 84 255 159  seagreen1\n 78 238 148  seagreen2\n 67 205 128  seagreen3\n 46 139  87  seagreen4\n255 245 238  seashell\n255 245 238  seashell1\n238 229 222  seashell2\n205 197 191  seashell3\n139 134 130  seashell4\n160  82  45  sienna\n255 130  71  sienna1\n238 121  66  sienna2\n205 104  57  sienna3\n139  71  38  sienna4\n135 206 235  skyblue\n135 206 255  skyblue1\n126 192 238  skyblue2\n108 166 205  skyblue3\n 74 112 139  skyblue4\n106  90 205  slateblue\n131 111 255  slateblue1\n122 103 238  slateblue2\n105  89 205  slateblue3\n 71  60 139  slateblue4\n112 128 144  slategray\n198 226 255  slategray1\n185 211 238  slategray2\n159 182 205  slategray3\n108 123 139  slategray4\n112 128 144  slategrey\n255 250 250  snow\n255 250 250  snow1\n238 233 233  snow2\n205 201 201  snow3\n139 137 137  snow4\n  0 255 127  springgreen\n  0 255 127  springgreen1\n  0 238 118  springgreen2\n  0 205 102  springgreen3\n  0 139  69  springgreen4\n 70 130 180  steelblue\n 99 184 255  steelblue1\n 92 172 238  steelblue2\n 79 148 205  steelblue3\n 54 100 139  steelblue4\n210 180 140  tan\n255 165  79  tan1\n238 154  73  tan2\n205 133  63  tan3\n139  90  43  tan4\n216 191 216  thistle\n255 225 255  thistle1\n238 210 238  thistle2\n205 181 205  thistle3\n139 123 139  thistle4\n255  99  71  tomato\n255  99  71  tomato1\n238  92  66  tomato2\n205  79  57  tomato3\n139  54  38  tomato4\n 64 224 208  turquoise\n  0 245 255  turquoise1\n  0 229 238  turquoise2\n  0 197 205  turquoise3\n  0 134 139  turquoise4\n238 130 238  violet\n208  32 144  violetred\n255  62 150  violetred1\n238  58 140  violetred2\n205  50 120  violetred3\n139  34  82  violetred4\n245 222 179  wheat\n255 231 186  wheat1\n238 216 174  wheat2\n205 186 150  wheat3\n139 126 102  wheat4\n255 255 255  white\n245 245 245  whitesmoke\n255 255   0  yellow\n255 255   0  yellow1\n238 238   0  yellow2\n205 205   0  yellow3\n139 139   0  yellow4\n154 205  50  yellowgreen"));
        for(String line = input.readLine(); line != null; line = input.readLine()) {
            final String[] words = line.trim().split("\\s+");
            if(words.length != 4)
                continue;
            final int rgb = 0xFF000000 | (Integer.parseInt(words[0]) << 16) | (Integer.parseInt(words[1]) << 8) | Integer.parseInt(words[2]);
            colors.put(words[3].toLowerCase(), rgb);
        }
        input.close();

        final PrintStream sysout = System.out;
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++) {
                main.invoke(null, (Object)new String[] {args[1], args[2], Integer.toString(x), Integer.toString(y)});
                System.out.print('|');
            }
        System.setOut(sysout);
        final String[] pixel = bos.toString().split("\\|");

        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int a = 0;
        for(int y = height - 1; y >= 0; y--)
            for(int x = 0; x < width; x++)
                try {
                    image.setRGB(x, y, colors.get(pixel[a++].trim()));
                }
                catch (final Exception ex) {
                    throw new RuntimeException(String.format("unknown pixel color name: \"%s\"", pixel[a]));
                }

        final FlagRender fr = new FlagRender();
        fr.load(image, clazz.toString());
        fr.repaint();

    }

}

