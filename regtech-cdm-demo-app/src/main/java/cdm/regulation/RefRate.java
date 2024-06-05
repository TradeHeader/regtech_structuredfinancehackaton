package cdm.regulation;

import cdm.regulation.RefRate;
import cdm.regulation.RefRate.RefRateBuilder;
import cdm.regulation.RefRate.RefRateBuilderImpl;
import cdm.regulation.RefRate.RefRateImpl;
import cdm.regulation.meta.RefRateMeta;
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
@RosettaDataType(value="RefRate", builder=RefRate.RefRateBuilderImpl.class, version="${project.version}")
public interface RefRate extends RosettaModelObject {

	RefRateMeta metaData = new RefRateMeta();

	/*********************** Getter Methods  ***********************/
	String getIndx();
	String getNm();

	/*********************** Build Methods  ***********************/
	RefRate build();
	
	RefRate.RefRateBuilder toBuilder();
	
	static RefRate.RefRateBuilder builder() {
		return new RefRate.RefRateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RefRate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RefRate> getType() {
		return RefRate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("indx"), String.class, getIndx(), this);
		processor.processBasic(path.newSubPath("nm"), String.class, getNm(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface RefRateBuilder extends RefRate, RosettaModelObjectBuilder {
		RefRate.RefRateBuilder setIndx(String indx);
		RefRate.RefRateBuilder setNm(String nm);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("indx"), String.class, getIndx(), this);
			processor.processBasic(path.newSubPath("nm"), String.class, getNm(), this);
		}
		

		RefRate.RefRateBuilder prune();
	}

	/*********************** Immutable Implementation of RefRate  ***********************/
	class RefRateImpl implements RefRate {
		private final String indx;
		private final String nm;
		
		protected RefRateImpl(RefRate.RefRateBuilder builder) {
			this.indx = builder.getIndx();
			this.nm = builder.getNm();
		}
		
		@Override
		@RosettaAttribute("indx")
		public String getIndx() {
			return indx;
		}
		
		@Override
		@RosettaAttribute("nm")
		public String getNm() {
			return nm;
		}
		
		@Override
		public RefRate build() {
			return this;
		}
		
		@Override
		public RefRate.RefRateBuilder toBuilder() {
			RefRate.RefRateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RefRate.RefRateBuilder builder) {
			ofNullable(getIndx()).ifPresent(builder::setIndx);
			ofNullable(getNm()).ifPresent(builder::setNm);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RefRate _that = getType().cast(o);
		
			if (!Objects.equals(indx, _that.getIndx())) return false;
			if (!Objects.equals(nm, _that.getNm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (indx != null ? indx.hashCode() : 0);
			_result = 31 * _result + (nm != null ? nm.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RefRate {" +
				"indx=" + this.indx + ", " +
				"nm=" + this.nm +
			'}';
		}
	}

	/*********************** Builder Implementation of RefRate  ***********************/
	class RefRateBuilderImpl implements RefRate.RefRateBuilder {
	
		protected String indx;
		protected String nm;
	
		public RefRateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("indx")
		public String getIndx() {
			return indx;
		}
		
		@Override
		@RosettaAttribute("nm")
		public String getNm() {
			return nm;
		}
		
	
		@Override
		@RosettaAttribute("indx")
		public RefRate.RefRateBuilder setIndx(String indx) {
			this.indx = indx==null?null:indx;
			return this;
		}
		@Override
		@RosettaAttribute("nm")
		public RefRate.RefRateBuilder setNm(String nm) {
			this.nm = nm==null?null:nm;
			return this;
		}
		
		@Override
		public RefRate build() {
			return new RefRate.RefRateImpl(this);
		}
		
		@Override
		public RefRate.RefRateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RefRate.RefRateBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIndx()!=null) return true;
			if (getNm()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RefRate.RefRateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RefRate.RefRateBuilder o = (RefRate.RefRateBuilder) other;
			
			
			merger.mergeBasic(getIndx(), o.getIndx(), this::setIndx);
			merger.mergeBasic(getNm(), o.getNm(), this::setNm);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RefRate _that = getType().cast(o);
		
			if (!Objects.equals(indx, _that.getIndx())) return false;
			if (!Objects.equals(nm, _that.getNm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (indx != null ? indx.hashCode() : 0);
			_result = 31 * _result + (nm != null ? nm.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RefRateBuilder {" +
				"indx=" + this.indx + ", " +
				"nm=" + this.nm +
			'}';
		}
	}
}
