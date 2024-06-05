package cdm.product.asset;

import cdm.product.asset.BoundedVariance;
import cdm.product.asset.VarianceCapFloor;
import cdm.product.asset.VarianceCapFloor.VarianceCapFloorBuilder;
import cdm.product.asset.VarianceCapFloor.VarianceCapFloorBuilderImpl;
import cdm.product.asset.VarianceCapFloor.VarianceCapFloorImpl;
import cdm.product.asset.meta.VarianceCapFloorMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="VarianceCapFloor", builder=VarianceCapFloor.VarianceCapFloorBuilderImpl.class, version="${project.version}")
public interface VarianceCapFloor extends RosettaModelObject {

	VarianceCapFloorMeta metaData = new VarianceCapFloorMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * If present and true, then variance cap is applicable.
	 */
	Boolean getVarianceCap();
	/**
	 * For use when varianceCap is applicable. Contains the scaling factor of the Variance Cap that can differ on a trade-by-trade basis in the European market. For example, a Variance Cap of 2.5^2 x Variance Strike Price has an unadjustedVarianceCap of 2.5.
	 */
	BigDecimal getUnadjustedVarianceCap();
	/**
	 * Conditions which bound variance. The contract specifies one or more boundary levels. These levels are expressed as prices for confirmation purposes underlier price must be equal to or higher than Lower Barrier is known as Up Conditional Swap underlier price must be equal to or lower than Upper Barrier is known as Down Conditional Swap underlier price must be equal to or higher than Lower Barrier and must be equal to or lower than Upper Barrier is known as Barrier Conditional Swap.
	 */
	BoundedVariance getBoundedVariance();

	/*********************** Build Methods  ***********************/
	VarianceCapFloor build();
	
	VarianceCapFloor.VarianceCapFloorBuilder toBuilder();
	
	static VarianceCapFloor.VarianceCapFloorBuilder builder() {
		return new VarianceCapFloor.VarianceCapFloorBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends VarianceCapFloor> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends VarianceCapFloor> getType() {
		return VarianceCapFloor.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("varianceCap"), Boolean.class, getVarianceCap(), this);
		processor.processBasic(path.newSubPath("unadjustedVarianceCap"), BigDecimal.class, getUnadjustedVarianceCap(), this);
		processRosetta(path.newSubPath("boundedVariance"), processor, BoundedVariance.class, getBoundedVariance());
	}
	

	/*********************** Builder Interface  ***********************/
	interface VarianceCapFloorBuilder extends VarianceCapFloor, RosettaModelObjectBuilder {
		BoundedVariance.BoundedVarianceBuilder getOrCreateBoundedVariance();
		BoundedVariance.BoundedVarianceBuilder getBoundedVariance();
		VarianceCapFloor.VarianceCapFloorBuilder setVarianceCap(Boolean varianceCap);
		VarianceCapFloor.VarianceCapFloorBuilder setUnadjustedVarianceCap(BigDecimal unadjustedVarianceCap);
		VarianceCapFloor.VarianceCapFloorBuilder setBoundedVariance(BoundedVariance boundedVariance);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("varianceCap"), Boolean.class, getVarianceCap(), this);
			processor.processBasic(path.newSubPath("unadjustedVarianceCap"), BigDecimal.class, getUnadjustedVarianceCap(), this);
			processRosetta(path.newSubPath("boundedVariance"), processor, BoundedVariance.BoundedVarianceBuilder.class, getBoundedVariance());
		}
		

		VarianceCapFloor.VarianceCapFloorBuilder prune();
	}

	/*********************** Immutable Implementation of VarianceCapFloor  ***********************/
	class VarianceCapFloorImpl implements VarianceCapFloor {
		private final Boolean varianceCap;
		private final BigDecimal unadjustedVarianceCap;
		private final BoundedVariance boundedVariance;
		
		protected VarianceCapFloorImpl(VarianceCapFloor.VarianceCapFloorBuilder builder) {
			this.varianceCap = builder.getVarianceCap();
			this.unadjustedVarianceCap = builder.getUnadjustedVarianceCap();
			this.boundedVariance = ofNullable(builder.getBoundedVariance()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("varianceCap")
		public Boolean getVarianceCap() {
			return varianceCap;
		}
		
		@Override
		@RosettaAttribute("unadjustedVarianceCap")
		public BigDecimal getUnadjustedVarianceCap() {
			return unadjustedVarianceCap;
		}
		
		@Override
		@RosettaAttribute("boundedVariance")
		public BoundedVariance getBoundedVariance() {
			return boundedVariance;
		}
		
		@Override
		public VarianceCapFloor build() {
			return this;
		}
		
		@Override
		public VarianceCapFloor.VarianceCapFloorBuilder toBuilder() {
			VarianceCapFloor.VarianceCapFloorBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(VarianceCapFloor.VarianceCapFloorBuilder builder) {
			ofNullable(getVarianceCap()).ifPresent(builder::setVarianceCap);
			ofNullable(getUnadjustedVarianceCap()).ifPresent(builder::setUnadjustedVarianceCap);
			ofNullable(getBoundedVariance()).ifPresent(builder::setBoundedVariance);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			VarianceCapFloor _that = getType().cast(o);
		
			if (!Objects.equals(varianceCap, _that.getVarianceCap())) return false;
			if (!Objects.equals(unadjustedVarianceCap, _that.getUnadjustedVarianceCap())) return false;
			if (!Objects.equals(boundedVariance, _that.getBoundedVariance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (varianceCap != null ? varianceCap.hashCode() : 0);
			_result = 31 * _result + (unadjustedVarianceCap != null ? unadjustedVarianceCap.hashCode() : 0);
			_result = 31 * _result + (boundedVariance != null ? boundedVariance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "VarianceCapFloor {" +
				"varianceCap=" + this.varianceCap + ", " +
				"unadjustedVarianceCap=" + this.unadjustedVarianceCap + ", " +
				"boundedVariance=" + this.boundedVariance +
			'}';
		}
	}

	/*********************** Builder Implementation of VarianceCapFloor  ***********************/
	class VarianceCapFloorBuilderImpl implements VarianceCapFloor.VarianceCapFloorBuilder {
	
		protected Boolean varianceCap;
		protected BigDecimal unadjustedVarianceCap;
		protected BoundedVariance.BoundedVarianceBuilder boundedVariance;
	
		public VarianceCapFloorBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("varianceCap")
		public Boolean getVarianceCap() {
			return varianceCap;
		}
		
		@Override
		@RosettaAttribute("unadjustedVarianceCap")
		public BigDecimal getUnadjustedVarianceCap() {
			return unadjustedVarianceCap;
		}
		
		@Override
		@RosettaAttribute("boundedVariance")
		public BoundedVariance.BoundedVarianceBuilder getBoundedVariance() {
			return boundedVariance;
		}
		
		@Override
		public BoundedVariance.BoundedVarianceBuilder getOrCreateBoundedVariance() {
			BoundedVariance.BoundedVarianceBuilder result;
			if (boundedVariance!=null) {
				result = boundedVariance;
			}
			else {
				result = boundedVariance = BoundedVariance.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("varianceCap")
		public VarianceCapFloor.VarianceCapFloorBuilder setVarianceCap(Boolean varianceCap) {
			this.varianceCap = varianceCap==null?null:varianceCap;
			return this;
		}
		@Override
		@RosettaAttribute("unadjustedVarianceCap")
		public VarianceCapFloor.VarianceCapFloorBuilder setUnadjustedVarianceCap(BigDecimal unadjustedVarianceCap) {
			this.unadjustedVarianceCap = unadjustedVarianceCap==null?null:unadjustedVarianceCap;
			return this;
		}
		@Override
		@RosettaAttribute("boundedVariance")
		public VarianceCapFloor.VarianceCapFloorBuilder setBoundedVariance(BoundedVariance boundedVariance) {
			this.boundedVariance = boundedVariance==null?null:boundedVariance.toBuilder();
			return this;
		}
		
		@Override
		public VarianceCapFloor build() {
			return new VarianceCapFloor.VarianceCapFloorImpl(this);
		}
		
		@Override
		public VarianceCapFloor.VarianceCapFloorBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public VarianceCapFloor.VarianceCapFloorBuilder prune() {
			if (boundedVariance!=null && !boundedVariance.prune().hasData()) boundedVariance = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getVarianceCap()!=null) return true;
			if (getUnadjustedVarianceCap()!=null) return true;
			if (getBoundedVariance()!=null && getBoundedVariance().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public VarianceCapFloor.VarianceCapFloorBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			VarianceCapFloor.VarianceCapFloorBuilder o = (VarianceCapFloor.VarianceCapFloorBuilder) other;
			
			merger.mergeRosetta(getBoundedVariance(), o.getBoundedVariance(), this::setBoundedVariance);
			
			merger.mergeBasic(getVarianceCap(), o.getVarianceCap(), this::setVarianceCap);
			merger.mergeBasic(getUnadjustedVarianceCap(), o.getUnadjustedVarianceCap(), this::setUnadjustedVarianceCap);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			VarianceCapFloor _that = getType().cast(o);
		
			if (!Objects.equals(varianceCap, _that.getVarianceCap())) return false;
			if (!Objects.equals(unadjustedVarianceCap, _that.getUnadjustedVarianceCap())) return false;
			if (!Objects.equals(boundedVariance, _that.getBoundedVariance())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (varianceCap != null ? varianceCap.hashCode() : 0);
			_result = 31 * _result + (unadjustedVarianceCap != null ? unadjustedVarianceCap.hashCode() : 0);
			_result = 31 * _result + (boundedVariance != null ? boundedVariance.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "VarianceCapFloorBuilder {" +
				"varianceCap=" + this.varianceCap + ", " +
				"unadjustedVarianceCap=" + this.unadjustedVarianceCap + ", " +
				"boundedVariance=" + this.boundedVariance +
			'}';
		}
	}
}
