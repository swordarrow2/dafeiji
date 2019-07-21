package com.InsProcess;

public class zunRunner {
	Ecl ecl;
	public zunRunner() {
		ecl = new Ecl();
		final Sub s1=ecl.sub("testSub", 2);
		s1.addIns(new Runnable(){

			  @Override
			  public void run() {
				  Ins600 ins=s1.ins600();
				  ins._600();
				  ins._602(2, 5);
				  ins._603(64, 64);
				  ins._601();
				  ins._600();
				  ins._602(4, 2);
				  ins._603(85, 12);
				  ins._601();
				  ins._601(1);
				}
			});


		final Sub s2=ecl.sub("MainFront");
		s2.addIns(new Runnable(){

			  @Override
			  public void run() {
				  VarIns v = s2.varIns().argCount(4);
				  Ins600 i6=s2.ins600()
					.	_600()
					.	posAndImg(64, 64, 2, 5)
					.	_601()
					.	_600();
				  Ins000 i0=s2.ins000();
				  v.assiInt("A", 64).assiFloat("B", 54).assiInt("C", 3).assiInt("D", 7);
				  i0.diffSwitch(3, 6, 9, 12, 15);
				  i6.	posAndImg(i0.getStack(-1), i0.getStack(-1), "2", "5");
				  i0._11("testSub", i0.transferInt("A"), i0.transferFloat("B"));
				  i6.	_602(4, 2)
					.	_603(85, 12)
					.	_601()
					.	_601(1);
				}
			});

	  }

	@Override
	public String toString() {
		return ecl.toString();
	  }

  }
