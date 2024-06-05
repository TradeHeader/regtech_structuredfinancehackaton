package cdm.regulation;

import cdm.regulation.DerivInstrmAttrbts;
import cdm.regulation.DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder;
import cdm.regulation.DerivInstrmAttrbts.DerivInstrmAttrbtsBuilderImpl;
import cdm.regulation.DerivInstrmAttrbts.DerivInstrmAttrbtsImpl;
import cdm.regulation.UndrlygInstrm;
import cdm.regulation.meta.DerivInstrmAttrbtsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="DerivInstrmAttrbts", builder=DerivInstrmAttrbts.DerivInstrmAttrbtsBuilderImpl.class, version="${project.version}")
public interface DerivInstrmAttrbts extends RosettaModelObject {

	DerivInstrmAttrbtsMeta metaData = new DerivInstrmAttrbtsMeta();

	/*********************** Getter Methods  ***********************/
	String getXpryDt();
	String getPricMltplr();
	UndrlygInstrm getUndrlygInstrm();
	String getDlvryTp();

	/*********************** Build Methods  ***********************/
	DerivInstrmAttrbts build();
	
	DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder toBuilder();
	
	static DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder builder() {
		return new DerivInstrmAttrbts.DerivInstrmAttrbtsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DerivInstrmAttrbts> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DerivInstrmAttrbts> getType() {
		return DerivInstrmAttrbts.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("xpryDt"), String.class, getXpryDt(), this);
		processor.processBasic(path.newSubPath("pricMltplr"), String.class, getPricMltplr(), this);
		processRosetta(path.newSubPath("undrlygInstrm"), processor, UndrlygInstrm.class, getUndrlygInstrm());
		processor.processBasic(path.newSubPath("dlvryTp"), String.class, getDlvryTp(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface DerivInstrmAttrbtsBuilder extends DerivInstrmAttrbts, RosettaModelObjectBuilder {
		UndrlygInstrm.UndrlygInstrmBuilder getOrCreateUndrlygInstrm();
		UndrlygInstrm.UndrlygInstrmBuilder getUndrlygInstrm();
		DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setXpryDt(String xpryDt);
		DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setPricMltplr(String pricMltplr);
		DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setUndrlygInstrm(UndrlygInstrm undrlygInstrm);
		DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setDlvryTp(String dlvryTp);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("xpryDt"), String.class, getXpryDt(), this);
			processor.processBasic(path.newSubPath("pricMltplr"), String.class, getPricMltplr(), this);
			processRosetta(path.newSubPath("undrlygInstrm"), processor, UndrlygInstrm.UndrlygInstrmBuilder.class, getUndrlygInstrm());
			processor.processBasic(path.newSubPath("dlvryTp"), String.class, getDlvryTp(), this);
		}
		

		DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder prune();
	}

	/*********************** Immutable Implementation of DerivInstrmAttrbts  ***********************/
	class DerivInstrmAttrbtsImpl implements DerivInstrmAttrbts {
		private final String xpryDt;
		private final String pricMltplr;
		private final UndrlygInstrm undrlygInstrm;
		private final String dlvryTp;
		
		protected DerivInstrmAttrbtsImpl(DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder builder) {
			this.xpryDt = builder.getXpryDt();
			this.pricMltplr = builder.getPricMltplr();
			this.undrlygInstrm = ofNullable(builder.getUndrlygInstrm()).map(f->f.build()).orElse(null);
			this.dlvryTp = builder.getDlvryTp();
		}
		
		@Override
		@RosettaAttribute("xpryDt")
		public String getXpryDt() {
			return xpryDt;
		}
		
		@Override
		@RosettaAttribute("pricMltplr")
		public String getPricMltplr() {
			return pricMltplr;
		}
		
		@Override
		@RosettaAttribute("undrlygInstrm")
		public UndrlygInstrm getUndrlygInstrm() {
			return undrlygInstrm;
		}
		
		@Override
		@RosettaAttribute("dlvryTp")
		public String getDlvryTp() {
			return dlvryTp;
		}
		
		@Override
		public DerivInstrmAttrbts build() {
			return this;
		}
		
		@Override
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder toBuilder() {
			DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder builder) {
			ofNullable(getXpryDt()).ifPresent(builder::setXpryDt);
			ofNullable(getPricMltplr()).ifPresent(builder::setPricMltplr);
			ofNullable(getUndrlygInstrm()).ifPresent(builder::setUndrlygInstrm);
			ofNullable(getDlvryTp()).ifPresent(builder::setDlvryTp);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DerivInstrmAttrbts _that = getType().cast(o);
		
			if (!Objects.equals(xpryDt, _that.getXpryDt())) return false;
			if (!Objects.equals(pricMltplr, _that.getPricMltplr())) return false;
			if (!Objects.equals(undrlygInstrm, _that.getUndrlygInstrm())) return false;
			if (!Objects.equals(dlvryTp, _that.getDlvryTp())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (xpryDt != null ? xpryDt.hashCode() : 0);
			_result = 31 * _result + (pricMltplr != null ? pricMltplr.hashCode() : 0);
			_result = 31 * _result + (undrlygInstrm != null ? undrlygInstrm.hashCode() : 0);
			_result = 31 * _result + (dlvryTp != null ? dlvryTp.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DerivInstrmAttrbts {" +
				"xpryDt=" + this.xpryDt + ", " +
				"pricMltplr=" + this.pricMltplr + ", " +
				"undrlygInstrm=" + this.undrlygInstrm + ", " +
				"dlvryTp=" + this.dlvryTp +
			'}';
		}
	}

	/*********************** Builder Implementation of DerivInstrmAttrbts  ***********************/
	class DerivInstrmAttrbtsBuilderImpl implements DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder {
	
		protected String xpryDt;
		protected String pricMltplr;
		protected UndrlygInstrm.UndrlygInstrmBuilder undrlygInstrm;
		protected String dlvryTp;
	
		public DerivInstrmAttrbtsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("xpryDt")
		public String getXpryDt() {
			return xpryDt;
		}
		
		@Override
		@RosettaAttribute("pricMltplr")
		public String getPricMltplr() {
			return pricMltplr;
		}
		
		@Override
		@RosettaAttribute("undrlygInstrm")
		public UndrlygInstrm.UndrlygInstrmBuilder getUndrlygInstrm() {
			return undrlygInstrm;
		}
		
		@Override
		public UndrlygInstrm.UndrlygInstrmBuilder getOrCreateUndrlygInstrm() {
			UndrlygInstrm.UndrlygInstrmBuilder result;
			if (undrlygInstrm!=null) {
				result = undrlygInstrm;
			}
			else {
				result = undrlygInstrm = UndrlygInstrm.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dlvryTp")
		public String getDlvryTp() {
			return dlvryTp;
		}
		
	
		@Override
		@RosettaAttribute("xpryDt")
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setXpryDt(String xpryDt) {
			this.xpryDt = xpryDt==null?null:xpryDt;
			return this;
		}
		@Override
		@RosettaAttribute("pricMltplr")
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setPricMltplr(String pricMltplr) {
			this.pricMltplr = pricMltplr==null?null:pricMltplr;
			return this;
		}
		@Override
		@RosettaAttribute("undrlygInstrm")
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setUndrlygInstrm(UndrlygInstrm undrlygInstrm) {
			this.undrlygInstrm = undrlygInstrm==null?null:undrlygInstrm.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dlvryTp")
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder setDlvryTp(String dlvryTp) {
			this.dlvryTp = dlvryTp==null?null:dlvryTp;
			return this;
		}
		
		@Override
		public DerivInstrmAttrbts build() {
			return new DerivInstrmAttrbts.DerivInstrmAttrbtsImpl(this);
		}
		
		@Override
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder prune() {
			if (undrlygInstrm!=null && !undrlygInstrm.prune().hasData()) undrlygInstrm = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getXpryDt()!=null) return true;
			if (getPricMltplr()!=null) return true;
			if (getUndrlygInstrm()!=null && getUndrlygInstrm().hasData()) return true;
			if (getDlvryTp()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder o = (DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder) other;
			
			merger.mergeRosetta(getUndrlygInstrm(), o.getUndrlygInstrm(), this::setUndrlygInstrm);
			
			merger.mergeBasic(getXpryDt(), o.getXpryDt(), this::setXpryDt);
			merger.mergeBasic(getPricMltplr(), o.getPricMltplr(), this::setPricMltplr);
			merger.mergeBasic(getDlvryTp(), o.getDlvryTp(), this::setDlvryTp);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DerivInstrmAttrbts _that = getType().cast(o);
		
			if (!Objects.equals(xpryDt, _that.getXpryDt())) return false;
			if (!Objects.equals(pricMltplr, _that.getPricMltplr())) return false;
			if (!Objects.equals(undrlygInstrm, _that.getUndrlygInstrm())) return false;
			if (!Objects.equals(dlvryTp, _that.getDlvryTp())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (xpryDt != null ? xpryDt.hashCode() : 0);
			_result = 31 * _result + (pricMltplr != null ? pricMltplr.hashCode() : 0);
			_result = 31 * _result + (undrlygInstrm != null ? undrlygInstrm.hashCode() : 0);
			_result = 31 * _result + (dlvryTp != null ? dlvryTp.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DerivInstrmAttrbtsBuilder {" +
				"xpryDt=" + this.xpryDt + ", " +
				"pricMltplr=" + this.pricMltplr + ", " +
				"undrlygInstrm=" + this.undrlygInstrm + ", " +
				"dlvryTp=" + this.dlvryTp +
			'}';
		}
	}
}
