package cdm.product.template;

import cdm.product.common.schedule.AveragingPeriod;
import cdm.product.template.Asian;
import cdm.product.template.Asian.AsianBuilder;
import cdm.product.template.Asian.AsianBuilderImpl;
import cdm.product.template.Asian.AsianImpl;
import cdm.product.template.AveragingInOutEnum;
import cdm.product.template.meta.AsianMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * As per ISDA 2002 Definitions.
 * @version ${project.version}
 */
@RosettaDataType(value="Asian", builder=Asian.AsianBuilderImpl.class, version="${project.version}")
public interface Asian extends RosettaModelObject {

	AsianMeta metaData = new AsianMeta();

	/*********************** Getter Methods  ***********************/
	AveragingInOutEnum getAveragingInOut();
	/**
	 * The factor of strike.
	 */
	BigDecimal getStrikeFactor();
	/**
	 * The averaging in period.
	 */
	AveragingPeriod getAveragingPeriodIn();
	/**
	 * The averaging out period.
	 */
	AveragingPeriod getAveragingPeriodOut();

	/*********************** Build Methods  ***********************/
	Asian build();
	
	Asian.AsianBuilder toBuilder();
	
	static Asian.AsianBuilder builder() {
		return new Asian.AsianBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Asian> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Asian> getType() {
		return Asian.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("averagingInOut"), AveragingInOutEnum.class, getAveragingInOut(), this);
		processor.processBasic(path.newSubPath("strikeFactor"), BigDecimal.class, getStrikeFactor(), this);
		processRosetta(path.newSubPath("averagingPeriodIn"), processor, AveragingPeriod.class, getAveragingPeriodIn());
		processRosetta(path.newSubPath("averagingPeriodOut"), processor, AveragingPeriod.class, getAveragingPeriodOut());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AsianBuilder extends Asian, RosettaModelObjectBuilder {
		AveragingPeriod.AveragingPeriodBuilder getOrCreateAveragingPeriodIn();
		AveragingPeriod.AveragingPeriodBuilder getAveragingPeriodIn();
		AveragingPeriod.AveragingPeriodBuilder getOrCreateAveragingPeriodOut();
		AveragingPeriod.AveragingPeriodBuilder getAveragingPeriodOut();
		Asian.AsianBuilder setAveragingInOut(AveragingInOutEnum averagingInOut);
		Asian.AsianBuilder setStrikeFactor(BigDecimal strikeFactor);
		Asian.AsianBuilder setAveragingPeriodIn(AveragingPeriod averagingPeriodIn);
		Asian.AsianBuilder setAveragingPeriodOut(AveragingPeriod averagingPeriodOut);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("averagingInOut"), AveragingInOutEnum.class, getAveragingInOut(), this);
			processor.processBasic(path.newSubPath("strikeFactor"), BigDecimal.class, getStrikeFactor(), this);
			processRosetta(path.newSubPath("averagingPeriodIn"), processor, AveragingPeriod.AveragingPeriodBuilder.class, getAveragingPeriodIn());
			processRosetta(path.newSubPath("averagingPeriodOut"), processor, AveragingPeriod.AveragingPeriodBuilder.class, getAveragingPeriodOut());
		}
		

		Asian.AsianBuilder prune();
	}

	/*********************** Immutable Implementation of Asian  ***********************/
	class AsianImpl implements Asian {
		private final AveragingInOutEnum averagingInOut;
		private final BigDecimal strikeFactor;
		private final AveragingPeriod averagingPeriodIn;
		private final AveragingPeriod averagingPeriodOut;
		
		protected AsianImpl(Asian.AsianBuilder builder) {
			this.averagingInOut = builder.getAveragingInOut();
			this.strikeFactor = builder.getStrikeFactor();
			this.averagingPeriodIn = ofNullable(builder.getAveragingPeriodIn()).map(f->f.build()).orElse(null);
			this.averagingPeriodOut = ofNullable(builder.getAveragingPeriodOut()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("averagingInOut")
		public AveragingInOutEnum getAveragingInOut() {
			return averagingInOut;
		}
		
		@Override
		@RosettaAttribute("strikeFactor")
		public BigDecimal getStrikeFactor() {
			return strikeFactor;
		}
		
		@Override
		@RosettaAttribute("averagingPeriodIn")
		public AveragingPeriod getAveragingPeriodIn() {
			return averagingPeriodIn;
		}
		
		@Override
		@RosettaAttribute("averagingPeriodOut")
		public AveragingPeriod getAveragingPeriodOut() {
			return averagingPeriodOut;
		}
		
		@Override
		public Asian build() {
			return this;
		}
		
		@Override
		public Asian.AsianBuilder toBuilder() {
			Asian.AsianBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Asian.AsianBuilder builder) {
			ofNullable(getAveragingInOut()).ifPresent(builder::setAveragingInOut);
			ofNullable(getStrikeFactor()).ifPresent(builder::setStrikeFactor);
			ofNullable(getAveragingPeriodIn()).ifPresent(builder::setAveragingPeriodIn);
			ofNullable(getAveragingPeriodOut()).ifPresent(builder::setAveragingPeriodOut);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Asian _that = getType().cast(o);
		
			if (!Objects.equals(averagingInOut, _that.getAveragingInOut())) return false;
			if (!Objects.equals(strikeFactor, _that.getStrikeFactor())) return false;
			if (!Objects.equals(averagingPeriodIn, _that.getAveragingPeriodIn())) return false;
			if (!Objects.equals(averagingPeriodOut, _that.getAveragingPeriodOut())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (averagingInOut != null ? averagingInOut.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (strikeFactor != null ? strikeFactor.hashCode() : 0);
			_result = 31 * _result + (averagingPeriodIn != null ? averagingPeriodIn.hashCode() : 0);
			_result = 31 * _result + (averagingPeriodOut != null ? averagingPeriodOut.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Asian {" +
				"averagingInOut=" + this.averagingInOut + ", " +
				"strikeFactor=" + this.strikeFactor + ", " +
				"averagingPeriodIn=" + this.averagingPeriodIn + ", " +
				"averagingPeriodOut=" + this.averagingPeriodOut +
			'}';
		}
	}

	/*********************** Builder Implementation of Asian  ***********************/
	class AsianBuilderImpl implements Asian.AsianBuilder {
	
		protected AveragingInOutEnum averagingInOut;
		protected BigDecimal strikeFactor;
		protected AveragingPeriod.AveragingPeriodBuilder averagingPeriodIn;
		protected AveragingPeriod.AveragingPeriodBuilder averagingPeriodOut;
	
		public AsianBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("averagingInOut")
		public AveragingInOutEnum getAveragingInOut() {
			return averagingInOut;
		}
		
		@Override
		@RosettaAttribute("strikeFactor")
		public BigDecimal getStrikeFactor() {
			return strikeFactor;
		}
		
		@Override
		@RosettaAttribute("averagingPeriodIn")
		public AveragingPeriod.AveragingPeriodBuilder getAveragingPeriodIn() {
			return averagingPeriodIn;
		}
		
		@Override
		public AveragingPeriod.AveragingPeriodBuilder getOrCreateAveragingPeriodIn() {
			AveragingPeriod.AveragingPeriodBuilder result;
			if (averagingPeriodIn!=null) {
				result = averagingPeriodIn;
			}
			else {
				result = averagingPeriodIn = AveragingPeriod.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("averagingPeriodOut")
		public AveragingPeriod.AveragingPeriodBuilder getAveragingPeriodOut() {
			return averagingPeriodOut;
		}
		
		@Override
		public AveragingPeriod.AveragingPeriodBuilder getOrCreateAveragingPeriodOut() {
			AveragingPeriod.AveragingPeriodBuilder result;
			if (averagingPeriodOut!=null) {
				result = averagingPeriodOut;
			}
			else {
				result = averagingPeriodOut = AveragingPeriod.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("averagingInOut")
		public Asian.AsianBuilder setAveragingInOut(AveragingInOutEnum averagingInOut) {
			this.averagingInOut = averagingInOut==null?null:averagingInOut;
			return this;
		}
		@Override
		@RosettaAttribute("strikeFactor")
		public Asian.AsianBuilder setStrikeFactor(BigDecimal strikeFactor) {
			this.strikeFactor = strikeFactor==null?null:strikeFactor;
			return this;
		}
		@Override
		@RosettaAttribute("averagingPeriodIn")
		public Asian.AsianBuilder setAveragingPeriodIn(AveragingPeriod averagingPeriodIn) {
			this.averagingPeriodIn = averagingPeriodIn==null?null:averagingPeriodIn.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("averagingPeriodOut")
		public Asian.AsianBuilder setAveragingPeriodOut(AveragingPeriod averagingPeriodOut) {
			this.averagingPeriodOut = averagingPeriodOut==null?null:averagingPeriodOut.toBuilder();
			return this;
		}
		
		@Override
		public Asian build() {
			return new Asian.AsianImpl(this);
		}
		
		@Override
		public Asian.AsianBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Asian.AsianBuilder prune() {
			if (averagingPeriodIn!=null && !averagingPeriodIn.prune().hasData()) averagingPeriodIn = null;
			if (averagingPeriodOut!=null && !averagingPeriodOut.prune().hasData()) averagingPeriodOut = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAveragingInOut()!=null) return true;
			if (getStrikeFactor()!=null) return true;
			if (getAveragingPeriodIn()!=null && getAveragingPeriodIn().hasData()) return true;
			if (getAveragingPeriodOut()!=null && getAveragingPeriodOut().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Asian.AsianBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Asian.AsianBuilder o = (Asian.AsianBuilder) other;
			
			merger.mergeRosetta(getAveragingPeriodIn(), o.getAveragingPeriodIn(), this::setAveragingPeriodIn);
			merger.mergeRosetta(getAveragingPeriodOut(), o.getAveragingPeriodOut(), this::setAveragingPeriodOut);
			
			merger.mergeBasic(getAveragingInOut(), o.getAveragingInOut(), this::setAveragingInOut);
			merger.mergeBasic(getStrikeFactor(), o.getStrikeFactor(), this::setStrikeFactor);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Asian _that = getType().cast(o);
		
			if (!Objects.equals(averagingInOut, _that.getAveragingInOut())) return false;
			if (!Objects.equals(strikeFactor, _that.getStrikeFactor())) return false;
			if (!Objects.equals(averagingPeriodIn, _that.getAveragingPeriodIn())) return false;
			if (!Objects.equals(averagingPeriodOut, _that.getAveragingPeriodOut())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (averagingInOut != null ? averagingInOut.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (strikeFactor != null ? strikeFactor.hashCode() : 0);
			_result = 31 * _result + (averagingPeriodIn != null ? averagingPeriodIn.hashCode() : 0);
			_result = 31 * _result + (averagingPeriodOut != null ? averagingPeriodOut.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AsianBuilder {" +
				"averagingInOut=" + this.averagingInOut + ", " +
				"strikeFactor=" + this.strikeFactor + ", " +
				"averagingPeriodIn=" + this.averagingPeriodIn + ", " +
				"averagingPeriodOut=" + this.averagingPeriodOut +
			'}';
		}
	}
}
