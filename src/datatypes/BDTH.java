package datatypes;


import jcolibri.cbrcore.Attribute;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;

public class BDTH implements jcolibri.cbrcore.CaseComponent{
	public IETextOpenNLP tendencyTo,benefitFrom,disadvantageOf,hatredOf;
	public IETextOpenNLP getTendencyTo() {
		return tendencyTo;
	}
	public void setTendencyTo(IETextOpenNLP tendencyTo) {
		this.tendencyTo = tendencyTo;
	}
	public IETextOpenNLP getBenefitFrom() {
		return benefitFrom;
	}
	public void setBenefitFrom(IETextOpenNLP benefitFrom) {
		this.benefitFrom = benefitFrom;
	}
	public IETextOpenNLP getDisadvantageOf() {
		return disadvantageOf;
	}
	public void setDisadvantageOf(IETextOpenNLP disadvantageOf) {
		this.disadvantageOf = disadvantageOf;
	}
	public IETextOpenNLP getHatredOf() {
		return hatredOf;
	}
	public void setHatredOf(IETextOpenNLP hatredOf) {
		this.hatredOf = hatredOf;
	}
	public BDTH(IETextOpenNLP TendencyTo,IETextOpenNLP BenefitFrom,IETextOpenNLP DisadvantageOf,IETextOpenNLP HatredOf) {
		tendencyTo = TendencyTo;
		benefitFrom = BenefitFrom;
		disadvantageOf = DisadvantageOf;
		hatredOf = HatredOf;
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
