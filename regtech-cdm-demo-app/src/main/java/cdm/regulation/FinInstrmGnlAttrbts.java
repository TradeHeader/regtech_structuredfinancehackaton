package cdm.regulation;

import cdm.regulation.FinInstrmGnlAttrbts;
import cdm.regulation.FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder;
import cdm.regulation.FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilderImpl;
import cdm.regulation.FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsImpl;
import cdm.regulation.meta.FinInstrmGnlAttrbtsMeta;
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
@RosettaDataType(value="FinInstrmGnlAttrbts", builder=FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilderImpl.class, version="${project.version}")
public interface FinInstrmGnlAttrbts extends RosettaModelObject {

	FinInstrmGnlAttrbtsMeta metaData = new FinInstrmGnlAttrbtsMeta();

	/*********************** Getter Methods  ***********************/
	String getFullNm();
	String getClssfctnTp();
	String getNtnlCcy();

	/*********************** Build Methods  ***********************/
	FinInstrmGnlAttrbts build();
	
	FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder toBuilder();
	
	static FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder builder() {
		return new FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FinInstrmGnlAttrbts> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FinInstrmGnlAttrbts> getType() {
		return FinInstrmGnlAttrbts.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("fullNm"), String.class, getFullNm(), this);
		processor.processBasic(path.newSubPath("clssfctnTp"), String.class, getClssfctnTp(), this);
		processor.processBasic(path.newSubPath("ntnlCcy"), String.class, getNtnlCcy(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FinInstrmGnlAttrbtsBuilder extends FinInstrmGnlAttrbts, RosettaModelObjectBuilder {
		FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder setFullNm(String fullNm);
		FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder setClssfctnTp(String clssfctnTp);
		FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder setNtnlCcy(String ntnlCcy);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("fullNm"), String.class, getFullNm(), this);
			processor.processBasic(path.newSubPath("clssfctnTp"), String.class, getClssfctnTp(), this);
			processor.processBasic(path.newSubPath("ntnlCcy"), String.class, getNtnlCcy(), this);
		}
		

		FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder prune();
	}

	/*********************** Immutable Implementation of FinInstrmGnlAttrbts  ***********************/
	class FinInstrmGnlAttrbtsImpl implements FinInstrmGnlAttrbts {
		private final String fullNm;
		private final String clssfctnTp;
		private final String ntnlCcy;
		
		protected FinInstrmGnlAttrbtsImpl(FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder builder) {
			this.fullNm = builder.getFullNm();
			this.clssfctnTp = builder.getClssfctnTp();
			this.ntnlCcy = builder.getNtnlCcy();
		}
		
		@Override
		@RosettaAttribute("fullNm")
		public String getFullNm() {
			return fullNm;
		}
		
		@Override
		@RosettaAttribute("clssfctnTp")
		public String getClssfctnTp() {
			return clssfctnTp;
		}
		
		@Override
		@RosettaAttribute("ntnlCcy")
		public String getNtnlCcy() {
			return ntnlCcy;
		}
		
		@Override
		public FinInstrmGnlAttrbts build() {
			return this;
		}
		
		@Override
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder toBuilder() {
			FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder builder) {
			ofNullable(getFullNm()).ifPresent(builder::setFullNm);
			ofNullable(getClssfctnTp()).ifPresent(builder::setClssfctnTp);
			ofNullable(getNtnlCcy()).ifPresent(builder::setNtnlCcy);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FinInstrmGnlAttrbts _that = getType().cast(o);
		
			if (!Objects.equals(fullNm, _that.getFullNm())) return false;
			if (!Objects.equals(clssfctnTp, _that.getClssfctnTp())) return false;
			if (!Objects.equals(ntnlCcy, _that.getNtnlCcy())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fullNm != null ? fullNm.hashCode() : 0);
			_result = 31 * _result + (clssfctnTp != null ? clssfctnTp.hashCode() : 0);
			_result = 31 * _result + (ntnlCcy != null ? ntnlCcy.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FinInstrmGnlAttrbts {" +
				"fullNm=" + this.fullNm + ", " +
				"clssfctnTp=" + this.clssfctnTp + ", " +
				"ntnlCcy=" + this.ntnlCcy +
			'}';
		}
	}

	/*********************** Builder Implementation of FinInstrmGnlAttrbts  ***********************/
	class FinInstrmGnlAttrbtsBuilderImpl implements FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder {
	
		protected String fullNm;
		protected String clssfctnTp;
		protected String ntnlCcy;
	
		public FinInstrmGnlAttrbtsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("fullNm")
		public String getFullNm() {
			return fullNm;
		}
		
		@Override
		@RosettaAttribute("clssfctnTp")
		public String getClssfctnTp() {
			return clssfctnTp;
		}
		
		@Override
		@RosettaAttribute("ntnlCcy")
		public String getNtnlCcy() {
			return ntnlCcy;
		}
		
	
		@Override
		@RosettaAttribute("fullNm")
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder setFullNm(String fullNm) {
			this.fullNm = fullNm==null?null:fullNm;
			return this;
		}
		@Override
		@RosettaAttribute("clssfctnTp")
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder setClssfctnTp(String clssfctnTp) {
			this.clssfctnTp = clssfctnTp==null?null:clssfctnTp;
			return this;
		}
		@Override
		@RosettaAttribute("ntnlCcy")
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder setNtnlCcy(String ntnlCcy) {
			this.ntnlCcy = ntnlCcy==null?null:ntnlCcy;
			return this;
		}
		
		@Override
		public FinInstrmGnlAttrbts build() {
			return new FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsImpl(this);
		}
		
		@Override
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFullNm()!=null) return true;
			if (getClssfctnTp()!=null) return true;
			if (getNtnlCcy()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder o = (FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder) other;
			
			
			merger.mergeBasic(getFullNm(), o.getFullNm(), this::setFullNm);
			merger.mergeBasic(getClssfctnTp(), o.getClssfctnTp(), this::setClssfctnTp);
			merger.mergeBasic(getNtnlCcy(), o.getNtnlCcy(), this::setNtnlCcy);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FinInstrmGnlAttrbts _that = getType().cast(o);
		
			if (!Objects.equals(fullNm, _that.getFullNm())) return false;
			if (!Objects.equals(clssfctnTp, _that.getClssfctnTp())) return false;
			if (!Objects.equals(ntnlCcy, _that.getNtnlCcy())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fullNm != null ? fullNm.hashCode() : 0);
			_result = 31 * _result + (clssfctnTp != null ? clssfctnTp.hashCode() : 0);
			_result = 31 * _result + (ntnlCcy != null ? ntnlCcy.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FinInstrmGnlAttrbtsBuilder {" +
				"fullNm=" + this.fullNm + ", " +
				"clssfctnTp=" + this.clssfctnTp + ", " +
				"ntnlCcy=" + this.ntnlCcy +
			'}';
		}
	}
}
