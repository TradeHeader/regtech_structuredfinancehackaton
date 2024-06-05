package cdm.product.collateral;

import cdm.base.datetime.Period;
import cdm.product.collateral.AverageTradingVolume;
import cdm.product.collateral.AverageTradingVolume.AverageTradingVolumeBuilder;
import cdm.product.collateral.AverageTradingVolume.AverageTradingVolumeBuilderImpl;
import cdm.product.collateral.AverageTradingVolume.AverageTradingVolumeImpl;
import cdm.product.collateral.AverageTradingVolumeMethodologyEnum;
import cdm.product.collateral.meta.AverageTradingVolumeMeta;
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
 * Represents the average trading volume of an Equity product upon an exchange or set of exchanges.
 * @version ${project.version}
 */
@RosettaDataType(value="AverageTradingVolume", builder=AverageTradingVolume.AverageTradingVolumeBuilderImpl.class, version="${project.version}")
public interface AverageTradingVolume extends RosettaModelObject {

	AverageTradingVolumeMeta metaData = new AverageTradingVolumeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents the period of the equities average trading volume on the exchange/s.
	 */
	Period getPeriod();
	/**
	 * Indicates the type of equity average trading volume being stated (single) the highest amount on one exchange, or (consolidated) volumes across multiple exchanges.
	 */
	AverageTradingVolumeMethodologyEnum getMethodology();

	/*********************** Build Methods  ***********************/
	AverageTradingVolume build();
	
	AverageTradingVolume.AverageTradingVolumeBuilder toBuilder();
	
	static AverageTradingVolume.AverageTradingVolumeBuilder builder() {
		return new AverageTradingVolume.AverageTradingVolumeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AverageTradingVolume> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AverageTradingVolume> getType() {
		return AverageTradingVolume.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("period"), processor, Period.class, getPeriod());
		processor.processBasic(path.newSubPath("methodology"), AverageTradingVolumeMethodologyEnum.class, getMethodology(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AverageTradingVolumeBuilder extends AverageTradingVolume, RosettaModelObjectBuilder {
		Period.PeriodBuilder getOrCreatePeriod();
		Period.PeriodBuilder getPeriod();
		AverageTradingVolume.AverageTradingVolumeBuilder setPeriod(Period period);
		AverageTradingVolume.AverageTradingVolumeBuilder setMethodology(AverageTradingVolumeMethodologyEnum methodology);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("period"), processor, Period.PeriodBuilder.class, getPeriod());
			processor.processBasic(path.newSubPath("methodology"), AverageTradingVolumeMethodologyEnum.class, getMethodology(), this);
		}
		

		AverageTradingVolume.AverageTradingVolumeBuilder prune();
	}

	/*********************** Immutable Implementation of AverageTradingVolume  ***********************/
	class AverageTradingVolumeImpl implements AverageTradingVolume {
		private final Period period;
		private final AverageTradingVolumeMethodologyEnum methodology;
		
		protected AverageTradingVolumeImpl(AverageTradingVolume.AverageTradingVolumeBuilder builder) {
			this.period = ofNullable(builder.getPeriod()).map(f->f.build()).orElse(null);
			this.methodology = builder.getMethodology();
		}
		
		@Override
		@RosettaAttribute("period")
		public Period getPeriod() {
			return period;
		}
		
		@Override
		@RosettaAttribute("methodology")
		public AverageTradingVolumeMethodologyEnum getMethodology() {
			return methodology;
		}
		
		@Override
		public AverageTradingVolume build() {
			return this;
		}
		
		@Override
		public AverageTradingVolume.AverageTradingVolumeBuilder toBuilder() {
			AverageTradingVolume.AverageTradingVolumeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AverageTradingVolume.AverageTradingVolumeBuilder builder) {
			ofNullable(getPeriod()).ifPresent(builder::setPeriod);
			ofNullable(getMethodology()).ifPresent(builder::setMethodology);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AverageTradingVolume _that = getType().cast(o);
		
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(methodology, _that.getMethodology())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (period != null ? period.hashCode() : 0);
			_result = 31 * _result + (methodology != null ? methodology.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AverageTradingVolume {" +
				"period=" + this.period + ", " +
				"methodology=" + this.methodology +
			'}';
		}
	}

	/*********************** Builder Implementation of AverageTradingVolume  ***********************/
	class AverageTradingVolumeBuilderImpl implements AverageTradingVolume.AverageTradingVolumeBuilder {
	
		protected Period.PeriodBuilder period;
		protected AverageTradingVolumeMethodologyEnum methodology;
	
		public AverageTradingVolumeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("period")
		public Period.PeriodBuilder getPeriod() {
			return period;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreatePeriod() {
			Period.PeriodBuilder result;
			if (period!=null) {
				result = period;
			}
			else {
				result = period = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("methodology")
		public AverageTradingVolumeMethodologyEnum getMethodology() {
			return methodology;
		}
		
	
		@Override
		@RosettaAttribute("period")
		public AverageTradingVolume.AverageTradingVolumeBuilder setPeriod(Period period) {
			this.period = period==null?null:period.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("methodology")
		public AverageTradingVolume.AverageTradingVolumeBuilder setMethodology(AverageTradingVolumeMethodologyEnum methodology) {
			this.methodology = methodology==null?null:methodology;
			return this;
		}
		
		@Override
		public AverageTradingVolume build() {
			return new AverageTradingVolume.AverageTradingVolumeImpl(this);
		}
		
		@Override
		public AverageTradingVolume.AverageTradingVolumeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AverageTradingVolume.AverageTradingVolumeBuilder prune() {
			if (period!=null && !period.prune().hasData()) period = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPeriod()!=null && getPeriod().hasData()) return true;
			if (getMethodology()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AverageTradingVolume.AverageTradingVolumeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AverageTradingVolume.AverageTradingVolumeBuilder o = (AverageTradingVolume.AverageTradingVolumeBuilder) other;
			
			merger.mergeRosetta(getPeriod(), o.getPeriod(), this::setPeriod);
			
			merger.mergeBasic(getMethodology(), o.getMethodology(), this::setMethodology);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AverageTradingVolume _that = getType().cast(o);
		
			if (!Objects.equals(period, _that.getPeriod())) return false;
			if (!Objects.equals(methodology, _that.getMethodology())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (period != null ? period.hashCode() : 0);
			_result = 31 * _result + (methodology != null ? methodology.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AverageTradingVolumeBuilder {" +
				"period=" + this.period + ", " +
				"methodology=" + this.methodology +
			'}';
		}
	}
}
