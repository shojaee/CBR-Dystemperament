package datatypes;

import jcolibri.cbrcore.Attribute;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;
import persianmedicine.cbrdystemp.DystempDescription.OL2;

public class PhysicalExamination implements jcolibri.cbrcore.CaseComponent{
	public String faceColor,salivationTime,mouthTaste,tongueColor;
	public OL2 limpness,saliva,salivation,tongueWettish;
	public IETextOpenNLP bodyState;
	
	public PhysicalExamination(IETextOpenNLP BodyState,String FaceColor,OL2 Limpness,String MouthTaste,OL2 Saliva,OL2 Salivation,String SalivationTime,String TongueColor,OL2 TongueWettish) {
		bodyState = BodyState;
		faceColor = FaceColor;
		limpness = Limpness;
		mouthTaste = MouthTaste;
		saliva = Saliva;
		salivation = Salivation;
		salivationTime = SalivationTime;
		tongueColor = TongueColor;
		tongueWettish = TongueWettish;
	}

	public String getFaceColor() {
		return faceColor;
	}

	public void setFaceColor(String faceColor) {
		this.faceColor = faceColor;
	}

	public String getSalivationTime() {
		return salivationTime;
	}

	public void setSalivationTime(String salivationTime) {
		this.salivationTime = salivationTime;
	}

	public String getMouthTaste() {
		return mouthTaste;
	}

	public void setMouthTaste(String mouthTaste) {
		this.mouthTaste = mouthTaste;
	}

	public String getTongueColor() {
		return tongueColor;
	}

	public void setTongueColor(String tongueColor) {
		this.tongueColor = tongueColor;
	}

	public OL2 getLimpness() {
		return limpness;
	}

	public void setLimpness(OL2 limpness) {
		this.limpness = limpness;
	}

	public OL2 getSaliva() {
		return saliva;
	}

	public void setSaliva(OL2 saliva) {
		this.saliva = saliva;
	}

	public OL2 getSalivation() {
		return salivation;
	}

	public void setSalivation(OL2 salivation) {
		this.salivation = salivation;
	}

	public OL2 getTongueWettish() {
		return tongueWettish;
	}

	public void setTongueWettish(OL2 tongueWettish) {
		this.tongueWettish = tongueWettish;
	}

	public IETextOpenNLP getBodyState() {
		return bodyState;
	}

	public void setBodyState(IETextOpenNLP bodyState) {
		this.bodyState = bodyState;
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
