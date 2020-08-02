package cs;

import cs.layer.*;
import java.util.*;

public class Layer {
	public static ArrayList<Layer> LayerArray = new ArrayList<Layer>();
	public static int total = 0;
	public static int selection = 0;
	public static int oldcolor = 0;
	public String name;
	public int sort;
	public boolean Visible;
	public int color;
	public int begin;
	public int end;
	public boolean NeedDelete;
	public ArrayList<Batch> BatchArray;
	public ArrayList<Lase> LaseArray;
	public ArrayList<Cover> CoverArray;
	public ArrayList<Rebound> ReboundArray;
	public ArrayList<Force> ForceArray;
	public ArrayList<Barrage> Barrages;
	public static void Clear() {
		total = 0;
		selection = 0;
		oldcolor = 0;
		LayerArray.clear();
	}
	public Layer(String nm, int bg, int ed) {
		name = nm;
		Visible = true;
		sort = LayerArray.size();
		selection = 0;
		color = oldcolor;
		++oldcolor;
		if (oldcolor > 6) {
			oldcolor = 0;
		}
		begin = bg;
		end = ed;
		++total;
		NeedDelete = false;
		BatchArray = new ArrayList<Batch>();
		LaseArray = new ArrayList<Lase>();
		CoverArray = new ArrayList<Cover>();
		ReboundArray = new ArrayList<Rebound>();
		ForceArray = new ArrayList<Force>();
		Barrages = new ArrayList<Barrage>();
		LayerArray.add(this);
	}
	public void Update() {
		if (!Main.Available) {
			return;
		}    
		if (!Visible) {
			return;
		}
		for (int index = 0;index < ForceArray.size();++index) {
			ForceArray.get(index).id = index;
			ForceArray.get(index).parentid = sort;
			if (!Time.Playing) {
				ForceArray.get(index).Update();
			} else {
				ForceArray.get(index).copys.Update();
			}
		}
		for (int index = 0;index < ReboundArray.size();++index) {
			ReboundArray.get(index).id = index;
			ReboundArray.get(index).parentid = this.sort;
			if (!Time.Playing) {
				ReboundArray.get(index).Update();
			} else {
				ReboundArray.get(index).copys.Update();
			}
		}
		for (int index = 0;index < CoverArray.size();++index) {
			CoverArray.get(index).id = index;
			CoverArray.get(index).parentid = sort;
			if (!Time.Playing) {
				CoverArray.get(index).Update();
			} else {
				CoverArray.get(index).copys.Update();
			}
		}
		for (int index = 0;index < LaseArray.size();++index) {
			LaseArray.get(index).id = index;
			LaseArray.get(index).parentid = sort;
			if (!Time.Playing) {
				LaseArray.get(index).Update();
			} else {
				LaseArray.get(index).copys.Update();
			}
		}
		for (int index = 0;index < BatchArray.size();++index) {
			BatchArray.get(index).id = index;
			BatchArray.get(index).parentid = sort;
			if (!Time.Playing) {
				BatchArray.get(index).Update();
			} else {
				BatchArray.get(index).copys.Update();
			}
		}
		if (!Time.Playing) {
			return;
		}
		for (int index = 0;index < Barrages.size();++index) {
			Barrages.get(index).id = index;
			Barrages.get(index).Update();
			Barrages.get(index).LUpdate();
		}
	}
}
