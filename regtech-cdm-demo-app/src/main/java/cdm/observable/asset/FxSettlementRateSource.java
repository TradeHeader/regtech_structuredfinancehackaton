package cdm.observable.asset;

import cdm.observable.asset.FxInformationSource;
import cdm.observable.asset.FxSettlementRateSource;
import cdm.observable.asset.FxSettlementRateSource.FxSettlementRateSourceBuilder;
import cdm.observable.asset.FxSettlementRateSource.FxSettlementRateSourceBuilderImpl;
import cdm.observable.asset.FxSettlementRateSource.FxSettlementRateSourceImpl;
import cdm.observable.asset.meta.FxSettlementRateSourceMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * The source of the Foreign Exchange settlement rate.
 * @version ${project.version}
 */
@RosettaDataType(value="FxSettlementRateSource", builder=FxSettlementRateSource.FxSettlementRateSourceBuilderImpl.class, version="${project.version}")
public interface FxSettlementRateSource extends RosettaModelObject {

	FxSettlementRateSourceMeta metaData = new FxSettlementRateSourceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates that an officially defined rate settlement rate option will be the used for the fixing.
	 */
	FieldWithMetaString getSettlementRateOption();
	/**
	 * Indicates that a non-standard rate source will be used for the fixing.
	 */
	FxInformationSource getNonstandardSettlementRate();

	/*********************** Build Methods  ***********************/
	FxSettlementRateSource build();
	
	FxSettlementRateSource.FxSettlementRateSourceBuilder toBuilder();
	
	static FxSettlementRateSource.FxSettlementRateSourceBuilder builder() {
		return new FxSettlementRateSource.FxSettlementRateSourceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FxSettlementRateSource> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FxSettlementRateSource> getType() {
		return FxSettlementRateSource.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("settlementRateOption"), processor, FieldWithMetaString.class, getSettlementRateOption());
		processRosetta(path.newSubPath("nonstandardSettlementRate"), processor, FxInformationSource.class, getNonstandardSettlementRate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FxSettlementRateSourceBuilder extends FxSettlementRateSource, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSettlementRateOption();
		FieldWithMetaString.FieldWithMetaStringBuilder getSettlementRateOption();
		FxInformationSource.FxInformationSourceBuilder getOrCreateNonstandardSettlementRate();
		FxInformationSource.FxInformationSourceBuilder getNonstandardSettlementRate();
		FxSettlementRateSource.FxSettlementRateSourceBuilder setSettlementRateOption(FieldWithMetaString settlementRateOption0);
		FxSettlementRateSource.FxSettlementRateSourceBuilder setSettlementRateOptionValue(String settlementRateOption1);
		FxSettlementRateSource.FxSettlementRateSourceBuilder setNonstandardSettlementRate(FxInformationSource nonstandardSettlementRate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("settlementRateOption"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getSettlementRateOption());
			processRosetta(path.newSubPath("nonstandardSettlementRate"), processor, FxInformationSource.FxInformationSourceBuilder.class, getNonstandardSettlementRate());
		}
		

		FxSettlementRateSource.FxSettlementRateSourceBuilder prune();
	}

	/*********************** Immutable Implementation of FxSettlementRateSource  ***********************/
	class FxSettlementRateSourceImpl implements FxSettlementRateSource {
		private final FieldWithMetaString settlementRateOption;
		private final FxInformationSource nonstandardSettlementRate;
		
		protected FxSettlementRateSourceImpl(FxSettlementRateSource.FxSettlementRateSourceBuilder builder) {
			this.settlementRateOption = ofNullable(builder.getSettlementRateOption()).map(f->f.build()).orElse(null);
			this.nonstandardSettlementRate = ofNullable(builder.getNonstandardSettlementRate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("settlementRateOption")
		public FieldWithMetaString getSettlementRateOption() {
			return settlementRateOption;
		}
		
		@Override
		@RosettaAttribute("nonstandardSettlementRate")
		public FxInformationSource getNonstandardSettlementRate() {
			return nonstandardSettlementRate;
		}
		
		@Override
		public FxSettlementRateSource build() {
			return this;
		}
		
		@Override
		public FxSettlementRateSource.FxSettlementRateSourceBuilder toBuilder() {
			FxSettlementRateSource.FxSettlementRateSourceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FxSettlementRateSource.FxSettlementRateSourceBuilder builder) {
			ofNullable(getSettlementRateOption()).ifPresent(builder::setSettlementRateOption);
			ofNullable(getNonstandardSettlementRate()).ifPresent(builder::setNonstandardSettlementRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxSettlementRateSource _that = getType().cast(o);
		
			if (!Objects.equals(settlementRateOption, _that.getSettlementRateOption())) return false;
			if (!Objects.equals(nonstandardSettlementRate, _that.getNonstandardSettlementRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementRateOption != null ? settlementRateOption.hashCode() : 0);
			_result = 31 * _result + (nonstandardSettlementRate != null ? nonstandardSettlementRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxSettlementRateSource {" +
				"settlementRateOption=" + this.settlementRateOption + ", " +
				"nonstandardSettlementRate=" + this.nonstandardSettlementRate +
			'}';
		}
	}

	/*********************** Builder Implementation of FxSettlementRateSource  ***********************/
	class FxSettlementRateSourceBuilderImpl implements FxSettlementRateSource.FxSettlementRateSourceBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder settlementRateOption;
		protected FxInformationSource.FxInformationSourceBuilder nonstandardSettlementRate;
	
		public FxSettlementRateSourceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("settlementRateOption")
		public FieldWithMetaString.FieldWithMetaStringBuilder getSettlementRateOption() {
			return settlementRateOption;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateSettlementRateOption() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (settlementRateOption!=null) {
				result = settlementRateOption;
			}
			else {
				result = settlementRateOption = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("nonstandardSettlementRate")
		public FxInformationSource.FxInformationSourceBuilder getNonstandardSettlementRate() {
			return nonstandardSettlementRate;
		}
		
		@Override
		public FxInformationSource.FxInformationSourceBuilder getOrCreateNonstandardSettlementRate() {
			FxInformationSource.FxInformationSourceBuilder result;
			if (nonstandardSettlementRate!=null) {
				result = nonstandardSettlementRate;
			}
			else {
				result = nonstandardSettlementRate = FxInformationSource.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("settlementRateOption")
		public FxSettlementRateSource.FxSettlementRateSourceBuilder setSettlementRateOption(FieldWithMetaString settlementRateOption) {
			this.settlementRateOption = settlementRateOption==null?null:settlementRateOption.toBuilder();
			return this;
		}
		@Override
		public FxSettlementRateSource.FxSettlementRateSourceBuilder setSettlementRateOptionValue(String settlementRateOption) {
			this.getOrCreateSettlementRateOption().setValue(settlementRateOption);
			return this;
		}
		@Override
		@RosettaAttribute("nonstandardSettlementRate")
		public FxSettlementRateSource.FxSettlementRateSourceBuilder setNonstandardSettlementRate(FxInformationSource nonstandardSettlementRate) {
			this.nonstandardSettlementRate = nonstandardSettlementRate==null?null:nonstandardSettlementRate.toBuilder();
			return this;
		}
		
		@Override
		public FxSettlementRateSource build() {
			return new FxSettlementRateSource.FxSettlementRateSourceImpl(this);
		}
		
		@Override
		public FxSettlementRateSource.FxSettlementRateSourceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxSettlementRateSource.FxSettlementRateSourceBuilder prune() {
			if (settlementRateOption!=null && !settlementRateOption.prune().hasData()) settlementRateOption = null;
			if (nonstandardSettlementRate!=null && !nonstandardSettlementRate.prune().hasData()) nonstandardSettlementRate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSettlementRateOption()!=null) return true;
			if (getNonstandardSettlementRate()!=null && getNonstandardSettlementRate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxSettlementRateSource.FxSettlementRateSourceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FxSettlementRateSource.FxSettlementRateSourceBuilder o = (FxSettlementRateSource.FxSettlementRateSourceBuilder) other;
			
			merger.mergeRosetta(getSettlementRateOption(), o.getSettlementRateOption(), this::setSettlementRateOption);
			merger.mergeRosetta(getNonstandardSettlementRate(), o.getNonstandardSettlementRate(), this::setNonstandardSettlementRate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxSettlementRateSource _that = getType().cast(o);
		
			if (!Objects.equals(settlementRateOption, _that.getSettlementRateOption())) return false;
			if (!Objects.equals(nonstandardSettlementRate, _that.getNonstandardSettlementRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementRateOption != null ? settlementRateOption.hashCode() : 0);
			_result = 31 * _result + (nonstandardSettlementRate != null ? nonstandardSettlementRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxSettlementRateSourceBuilder {" +
				"settlementRateOption=" + this.settlementRateOption + ", " +
				"nonstandardSettlementRate=" + this.nonstandardSettlementRate +
			'}';
		}
	}
}
