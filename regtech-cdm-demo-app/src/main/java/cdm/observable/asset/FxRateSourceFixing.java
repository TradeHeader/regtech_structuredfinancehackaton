package cdm.observable.asset;

import cdm.base.datetime.AdjustableDate;
import cdm.observable.asset.FxRateSourceFixing;
import cdm.observable.asset.FxRateSourceFixing.FxRateSourceFixingBuilder;
import cdm.observable.asset.FxRateSourceFixing.FxRateSourceFixingBuilderImpl;
import cdm.observable.asset.FxRateSourceFixing.FxRateSourceFixingImpl;
import cdm.observable.asset.FxSettlementRateSource;
import cdm.observable.asset.meta.FxRateSourceFixingMeta;
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
 * Describes a rate source to be fixed and the date the fixing occurs
 * @version ${project.version}
 */
@RosettaDataType(value="FxRateSourceFixing", builder=FxRateSourceFixing.FxRateSourceFixingBuilderImpl.class, version="${project.version}")
public interface FxRateSourceFixing extends RosettaModelObject {

	FxRateSourceFixingMeta metaData = new FxRateSourceFixingMeta();

	/*********************** Getter Methods  ***********************/
	FxSettlementRateSource getSettlementRateSource();
	/**
	 * The date on which the fixing is scheduled to occur.
	 */
	AdjustableDate getFixingDate();

	/*********************** Build Methods  ***********************/
	FxRateSourceFixing build();
	
	FxRateSourceFixing.FxRateSourceFixingBuilder toBuilder();
	
	static FxRateSourceFixing.FxRateSourceFixingBuilder builder() {
		return new FxRateSourceFixing.FxRateSourceFixingBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FxRateSourceFixing> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FxRateSourceFixing> getType() {
		return FxRateSourceFixing.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("settlementRateSource"), processor, FxSettlementRateSource.class, getSettlementRateSource());
		processRosetta(path.newSubPath("fixingDate"), processor, AdjustableDate.class, getFixingDate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FxRateSourceFixingBuilder extends FxRateSourceFixing, RosettaModelObjectBuilder {
		FxSettlementRateSource.FxSettlementRateSourceBuilder getOrCreateSettlementRateSource();
		FxSettlementRateSource.FxSettlementRateSourceBuilder getSettlementRateSource();
		AdjustableDate.AdjustableDateBuilder getOrCreateFixingDate();
		AdjustableDate.AdjustableDateBuilder getFixingDate();
		FxRateSourceFixing.FxRateSourceFixingBuilder setSettlementRateSource(FxSettlementRateSource settlementRateSource);
		FxRateSourceFixing.FxRateSourceFixingBuilder setFixingDate(AdjustableDate fixingDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("settlementRateSource"), processor, FxSettlementRateSource.FxSettlementRateSourceBuilder.class, getSettlementRateSource());
			processRosetta(path.newSubPath("fixingDate"), processor, AdjustableDate.AdjustableDateBuilder.class, getFixingDate());
		}
		

		FxRateSourceFixing.FxRateSourceFixingBuilder prune();
	}

	/*********************** Immutable Implementation of FxRateSourceFixing  ***********************/
	class FxRateSourceFixingImpl implements FxRateSourceFixing {
		private final FxSettlementRateSource settlementRateSource;
		private final AdjustableDate fixingDate;
		
		protected FxRateSourceFixingImpl(FxRateSourceFixing.FxRateSourceFixingBuilder builder) {
			this.settlementRateSource = ofNullable(builder.getSettlementRateSource()).map(f->f.build()).orElse(null);
			this.fixingDate = ofNullable(builder.getFixingDate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("settlementRateSource")
		public FxSettlementRateSource getSettlementRateSource() {
			return settlementRateSource;
		}
		
		@Override
		@RosettaAttribute("fixingDate")
		public AdjustableDate getFixingDate() {
			return fixingDate;
		}
		
		@Override
		public FxRateSourceFixing build() {
			return this;
		}
		
		@Override
		public FxRateSourceFixing.FxRateSourceFixingBuilder toBuilder() {
			FxRateSourceFixing.FxRateSourceFixingBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FxRateSourceFixing.FxRateSourceFixingBuilder builder) {
			ofNullable(getSettlementRateSource()).ifPresent(builder::setSettlementRateSource);
			ofNullable(getFixingDate()).ifPresent(builder::setFixingDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxRateSourceFixing _that = getType().cast(o);
		
			if (!Objects.equals(settlementRateSource, _that.getSettlementRateSource())) return false;
			if (!Objects.equals(fixingDate, _that.getFixingDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementRateSource != null ? settlementRateSource.hashCode() : 0);
			_result = 31 * _result + (fixingDate != null ? fixingDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxRateSourceFixing {" +
				"settlementRateSource=" + this.settlementRateSource + ", " +
				"fixingDate=" + this.fixingDate +
			'}';
		}
	}

	/*********************** Builder Implementation of FxRateSourceFixing  ***********************/
	class FxRateSourceFixingBuilderImpl implements FxRateSourceFixing.FxRateSourceFixingBuilder {
	
		protected FxSettlementRateSource.FxSettlementRateSourceBuilder settlementRateSource;
		protected AdjustableDate.AdjustableDateBuilder fixingDate;
	
		public FxRateSourceFixingBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("settlementRateSource")
		public FxSettlementRateSource.FxSettlementRateSourceBuilder getSettlementRateSource() {
			return settlementRateSource;
		}
		
		@Override
		public FxSettlementRateSource.FxSettlementRateSourceBuilder getOrCreateSettlementRateSource() {
			FxSettlementRateSource.FxSettlementRateSourceBuilder result;
			if (settlementRateSource!=null) {
				result = settlementRateSource;
			}
			else {
				result = settlementRateSource = FxSettlementRateSource.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fixingDate")
		public AdjustableDate.AdjustableDateBuilder getFixingDate() {
			return fixingDate;
		}
		
		@Override
		public AdjustableDate.AdjustableDateBuilder getOrCreateFixingDate() {
			AdjustableDate.AdjustableDateBuilder result;
			if (fixingDate!=null) {
				result = fixingDate;
			}
			else {
				result = fixingDate = AdjustableDate.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("settlementRateSource")
		public FxRateSourceFixing.FxRateSourceFixingBuilder setSettlementRateSource(FxSettlementRateSource settlementRateSource) {
			this.settlementRateSource = settlementRateSource==null?null:settlementRateSource.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixingDate")
		public FxRateSourceFixing.FxRateSourceFixingBuilder setFixingDate(AdjustableDate fixingDate) {
			this.fixingDate = fixingDate==null?null:fixingDate.toBuilder();
			return this;
		}
		
		@Override
		public FxRateSourceFixing build() {
			return new FxRateSourceFixing.FxRateSourceFixingImpl(this);
		}
		
		@Override
		public FxRateSourceFixing.FxRateSourceFixingBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxRateSourceFixing.FxRateSourceFixingBuilder prune() {
			if (settlementRateSource!=null && !settlementRateSource.prune().hasData()) settlementRateSource = null;
			if (fixingDate!=null && !fixingDate.prune().hasData()) fixingDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSettlementRateSource()!=null && getSettlementRateSource().hasData()) return true;
			if (getFixingDate()!=null && getFixingDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxRateSourceFixing.FxRateSourceFixingBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FxRateSourceFixing.FxRateSourceFixingBuilder o = (FxRateSourceFixing.FxRateSourceFixingBuilder) other;
			
			merger.mergeRosetta(getSettlementRateSource(), o.getSettlementRateSource(), this::setSettlementRateSource);
			merger.mergeRosetta(getFixingDate(), o.getFixingDate(), this::setFixingDate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FxRateSourceFixing _that = getType().cast(o);
		
			if (!Objects.equals(settlementRateSource, _that.getSettlementRateSource())) return false;
			if (!Objects.equals(fixingDate, _that.getFixingDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementRateSource != null ? settlementRateSource.hashCode() : 0);
			_result = 31 * _result + (fixingDate != null ? fixingDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FxRateSourceFixingBuilder {" +
				"settlementRateSource=" + this.settlementRateSource + ", " +
				"fixingDate=" + this.fixingDate +
			'}';
		}
	}
}
