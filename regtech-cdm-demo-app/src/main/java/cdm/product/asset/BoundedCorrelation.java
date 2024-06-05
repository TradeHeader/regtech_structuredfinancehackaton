package cdm.product.asset;

import cdm.product.asset.BoundedCorrelation;
import cdm.product.asset.BoundedCorrelation.BoundedCorrelationBuilder;
import cdm.product.asset.BoundedCorrelation.BoundedCorrelationBuilderImpl;
import cdm.product.asset.BoundedCorrelation.BoundedCorrelationImpl;
import cdm.product.asset.meta.BoundedCorrelationMeta;
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
 * Describes correlation bounds, which form a cap and a floor on the realized correlation.
 * @version ${project.version}
 */
@RosettaDataType(value="BoundedCorrelation", builder=BoundedCorrelation.BoundedCorrelationBuilderImpl.class, version="${project.version}")
public interface BoundedCorrelation extends RosettaModelObject {

	BoundedCorrelationMeta metaData = new BoundedCorrelationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Minimum Boundary as a percentage of the Strike Price.
	 */
	BigDecimal getMinimumBoundaryPercent();
	/**
	 * Maximum Boundary as a percentage of the Strike Price.
	 */
	BigDecimal getMaximumBoundaryPercent();

	/*********************** Build Methods  ***********************/
	BoundedCorrelation build();
	
	BoundedCorrelation.BoundedCorrelationBuilder toBuilder();
	
	static BoundedCorrelation.BoundedCorrelationBuilder builder() {
		return new BoundedCorrelation.BoundedCorrelationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BoundedCorrelation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BoundedCorrelation> getType() {
		return BoundedCorrelation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("minimumBoundaryPercent"), BigDecimal.class, getMinimumBoundaryPercent(), this);
		processor.processBasic(path.newSubPath("maximumBoundaryPercent"), BigDecimal.class, getMaximumBoundaryPercent(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface BoundedCorrelationBuilder extends BoundedCorrelation, RosettaModelObjectBuilder {
		BoundedCorrelation.BoundedCorrelationBuilder setMinimumBoundaryPercent(BigDecimal minimumBoundaryPercent);
		BoundedCorrelation.BoundedCorrelationBuilder setMaximumBoundaryPercent(BigDecimal maximumBoundaryPercent);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("minimumBoundaryPercent"), BigDecimal.class, getMinimumBoundaryPercent(), this);
			processor.processBasic(path.newSubPath("maximumBoundaryPercent"), BigDecimal.class, getMaximumBoundaryPercent(), this);
		}
		

		BoundedCorrelation.BoundedCorrelationBuilder prune();
	}

	/*********************** Immutable Implementation of BoundedCorrelation  ***********************/
	class BoundedCorrelationImpl implements BoundedCorrelation {
		private final BigDecimal minimumBoundaryPercent;
		private final BigDecimal maximumBoundaryPercent;
		
		protected BoundedCorrelationImpl(BoundedCorrelation.BoundedCorrelationBuilder builder) {
			this.minimumBoundaryPercent = builder.getMinimumBoundaryPercent();
			this.maximumBoundaryPercent = builder.getMaximumBoundaryPercent();
		}
		
		@Override
		@RosettaAttribute("minimumBoundaryPercent")
		public BigDecimal getMinimumBoundaryPercent() {
			return minimumBoundaryPercent;
		}
		
		@Override
		@RosettaAttribute("maximumBoundaryPercent")
		public BigDecimal getMaximumBoundaryPercent() {
			return maximumBoundaryPercent;
		}
		
		@Override
		public BoundedCorrelation build() {
			return this;
		}
		
		@Override
		public BoundedCorrelation.BoundedCorrelationBuilder toBuilder() {
			BoundedCorrelation.BoundedCorrelationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BoundedCorrelation.BoundedCorrelationBuilder builder) {
			ofNullable(getMinimumBoundaryPercent()).ifPresent(builder::setMinimumBoundaryPercent);
			ofNullable(getMaximumBoundaryPercent()).ifPresent(builder::setMaximumBoundaryPercent);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BoundedCorrelation _that = getType().cast(o);
		
			if (!Objects.equals(minimumBoundaryPercent, _that.getMinimumBoundaryPercent())) return false;
			if (!Objects.equals(maximumBoundaryPercent, _that.getMaximumBoundaryPercent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (minimumBoundaryPercent != null ? minimumBoundaryPercent.hashCode() : 0);
			_result = 31 * _result + (maximumBoundaryPercent != null ? maximumBoundaryPercent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BoundedCorrelation {" +
				"minimumBoundaryPercent=" + this.minimumBoundaryPercent + ", " +
				"maximumBoundaryPercent=" + this.maximumBoundaryPercent +
			'}';
		}
	}

	/*********************** Builder Implementation of BoundedCorrelation  ***********************/
	class BoundedCorrelationBuilderImpl implements BoundedCorrelation.BoundedCorrelationBuilder {
	
		protected BigDecimal minimumBoundaryPercent;
		protected BigDecimal maximumBoundaryPercent;
	
		public BoundedCorrelationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("minimumBoundaryPercent")
		public BigDecimal getMinimumBoundaryPercent() {
			return minimumBoundaryPercent;
		}
		
		@Override
		@RosettaAttribute("maximumBoundaryPercent")
		public BigDecimal getMaximumBoundaryPercent() {
			return maximumBoundaryPercent;
		}
		
	
		@Override
		@RosettaAttribute("minimumBoundaryPercent")
		public BoundedCorrelation.BoundedCorrelationBuilder setMinimumBoundaryPercent(BigDecimal minimumBoundaryPercent) {
			this.minimumBoundaryPercent = minimumBoundaryPercent==null?null:minimumBoundaryPercent;
			return this;
		}
		@Override
		@RosettaAttribute("maximumBoundaryPercent")
		public BoundedCorrelation.BoundedCorrelationBuilder setMaximumBoundaryPercent(BigDecimal maximumBoundaryPercent) {
			this.maximumBoundaryPercent = maximumBoundaryPercent==null?null:maximumBoundaryPercent;
			return this;
		}
		
		@Override
		public BoundedCorrelation build() {
			return new BoundedCorrelation.BoundedCorrelationImpl(this);
		}
		
		@Override
		public BoundedCorrelation.BoundedCorrelationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BoundedCorrelation.BoundedCorrelationBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMinimumBoundaryPercent()!=null) return true;
			if (getMaximumBoundaryPercent()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BoundedCorrelation.BoundedCorrelationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BoundedCorrelation.BoundedCorrelationBuilder o = (BoundedCorrelation.BoundedCorrelationBuilder) other;
			
			
			merger.mergeBasic(getMinimumBoundaryPercent(), o.getMinimumBoundaryPercent(), this::setMinimumBoundaryPercent);
			merger.mergeBasic(getMaximumBoundaryPercent(), o.getMaximumBoundaryPercent(), this::setMaximumBoundaryPercent);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BoundedCorrelation _that = getType().cast(o);
		
			if (!Objects.equals(minimumBoundaryPercent, _that.getMinimumBoundaryPercent())) return false;
			if (!Objects.equals(maximumBoundaryPercent, _that.getMaximumBoundaryPercent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (minimumBoundaryPercent != null ? minimumBoundaryPercent.hashCode() : 0);
			_result = 31 * _result + (maximumBoundaryPercent != null ? maximumBoundaryPercent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BoundedCorrelationBuilder {" +
				"minimumBoundaryPercent=" + this.minimumBoundaryPercent + ", " +
				"maximumBoundaryPercent=" + this.maximumBoundaryPercent +
			'}';
		}
	}
}
