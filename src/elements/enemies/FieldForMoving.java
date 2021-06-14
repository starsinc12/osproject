package elements.enemies;

import GameStates.PlayState;
import backgrounds.PlayStateBack;

import java.io.IOException;
import java.util.ArrayList;

public class FieldForMoving {
    protected class TileForMoving {
        private int x;
        private int y;
        private boolean walkable;
        private boolean calculated;
        private TileForMoving takedFrom; //клатка из которой мы попали сюда
        private int PathLenght; //Ортогонально присваивается 10, по диагонали - 14
        private int HeuristicApproximation; //эвристическое приближение. расчитывается по формуле: (|Хклетки - Хитоговый| + |Yклетки - Yитоговый|) * 10 aka метод Манхетона
        private int ResultWeith;
    }
    private TileForMoving[][] tfm;

    public FieldForMoving() {
        tfm = new TileForMoving[20][20];
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                tfm[i][j] = new TileForMoving();
                tfm[i][j].x = i;
                tfm[i][j].y = j;
                tfm[i][j].walkable = PlayState.background.getTiles()[i][j].isWalkable();
                tfm[i][j].calculated = false;
            }
        }
    }

    /** 1 клетка. У неё суммируются путь и евристика */
    private void calculateWeith(int x, int y) {
        tfm[x][y].ResultWeith = tfm[x][y].PathLenght + tfm[x][y].HeuristicApproximation;
    }
    private void calculateWeith(TileForMoving a) {
        a.ResultWeith = a.PathLenght + a.HeuristicApproximation;
    }
    /** 3 клетки. В 1-ую записываем и считаем PathLenght и HeuristicApproximation, суммируем их. 2-ая клетка - предыдущая, 3-ая - итоговая*/
    private void calculateWeith(int x1, int y1, int x2, int y2, int x3, int y3) throws IOException{
        calculatePathLenght(x1, y1, x2, y2);
        calculateHeuristicApproximation(x1, y1, x3, y3);
        calculateWeith(x1, x2);
    }
    private void calculateWeith(TileForMoving a, TileForMoving b, TileForMoving c) throws IOException{
        calculatePathLenght(a.x, a.y, b.x, b.y);
        calculateHeuristicApproximation(a.x, a.y, c.x, c.y);
        calculateWeith(a.x, a.y);
    }

    /** 2 клетки. Считаем PathLenght в 1-ой клетке. 2-ая клетка - откуда пришли в 1-ую */
    private void calculatePathLenght(int x1, int y1, int x2, int y2) throws IOException {
        if (Math.abs(x1 - x2) > 1 || Math.abs(y1 - y2) > 1) {
            throw new IOException("Клетки должны соседствовать друг с другом! (calculatePathLenght)");
        }
        else {
            if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1)
                tfm[x1][y1].PathLenght = 14;
            else
                tfm[x1][y1].PathLenght = 10;
        }
    }
    private void calculatePathLenght(TileForMoving a, TileForMoving b) throws IOException{
        calculatePathLenght(a.x, a.y, b.x, b.y);
    }

    /** 2 клетки. Считаем HeuristicApproximation в 1-ой клетке. 2-ая клетка итоговая (куда нужно прийти). В 1-ую записывается евристика до 2-ой */
    private void calculateHeuristicApproximation(int x1, int y1, int x2, int y2) {
        tfm[x1][y1].HeuristicApproximation = (Math.abs(x1 - x2) + Math.abs(y1 - y2)) * 10;
    }
    private void calculateHeuristicApproximation(TileForMoving a, TileForMoving b) {
        calculateHeuristicApproximation(a.x, a.y, b.x, b.y);
    }

    /** 2 клетки. Записываем takedFrom в 1-ую клетку. Во 2-ую попадаем из 1-ой, поэтому записываем 1-ую как предыдущую ко 2-ой */
    private void setTakedFrom(int x1, int y1, int x2, int y2) throws IOException {
        if (Math.abs(x1 - x2) > 1 || Math.abs(y1 - y2) > 1) {
            throw new IOException("Клетки должны соседствовать друг с другом! (setTakedFrom)");
        }
        else {
            tfm[x1][y1].takedFrom = tfm[x2][y2];
        }
    }
    private void setTakedFrom(TileForMoving a, TileForMoving b) throws IOException {
        if (Math.abs(a.x - b.x) > 1 || Math.abs(a.y - b.y) > 1) {
            throw new IOException("Клетки должны соседствовать друг с другом! (setTakedFrom)");
        }
        else {
            a.takedFrom = b;
        }
    }

    /** 3 клетки. В 1-ую записываем и считаем PathLenght, HeuristicApproximation (суммируем их); записываем 2-ую как предыдущую. 2-ая клетка - предыдущая, 3-ая - итоговая */
    private void calculateTile(int x1, int y1, int x2, int y2, int x3, int y3) throws IOException{
        setTakedFrom(x1, y1, x2, y2);
        calculatePathLenght(x1, y1, x2, y2);
        calculateHeuristicApproximation(x1, y1, x3, y3);
        calculateWeith(x1, y1);
        tfm[x1][y1].calculated = true;
    }

    /** 2 клетки. В 1-ой находится враг, во 2-ой - герой */
    public ArrayList<int[]> findPath(int x1, int y1, int x2, int y2) {
        ArrayList<int[]> path = new ArrayList<>();
        ArrayList<int[]> truepath = new ArrayList<>();
        ArrayList<TileForMoving> queue = new ArrayList<>();
        TileForMoving cur = tfm[x1][y1];
        cur.ResultWeith = Integer.MAX_VALUE;
        queue.add(cur);
        if(x1 == x2 || y1 == y2) {
            int ii = 0;
            /*int[] tp = {cur.x, cur.y};
            truepath.add(tp);
            return truepath;*/
        }
        boolean find = false;
        while (!find) {
            for (int i = -1; i < 2; ++i) {
                for (int j = -1; j < 2; ++j) {
                    if (i == 0 && j == 0)
                        continue;

                    if (cur.x + i < 0 || cur.x + i > 19 || cur.y + j < 0 || cur.y + j > 19)
                        continue;

                    if (tfm[cur.x + i][cur.y + j].calculated || !tfm[cur.x + i][cur.y + j].walkable)
                        continue;

                    try {
                        calculateTile(cur.x + i, cur.y + j, cur.x, cur.y, x2, y2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    queue.add(tfm[cur.x + i][cur.y + j]);
                }
            }

            queue.remove(cur);
            cur.ResultWeith = queue.get(0).ResultWeith;
            for (TileForMoving a : queue) {
                if (cur.ResultWeith >= a.ResultWeith) {
                    cur = a;
                }
            }

            if (cur.x == x2 && cur.y == y2) {
                find = true;
                break;
            }
        }


        while(true) {
            int[] a = new int[2];
            a[0] = cur.x;
            a[1] = cur.y;
            path.add(a);
            cur = cur.takedFrom;
            if(cur.x == x1 && cur.y == y1) break;
        }

        int[] start = {x1, y1};
        path.add(start);
        for (int i = 0; i < path.size(); ++i) {
            truepath.add(path.get(path.size() - 1 - i));
        }
        return truepath;
    }
}
