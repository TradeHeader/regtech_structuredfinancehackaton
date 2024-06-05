package cdm.product.common.settlement;

import cdm.product.common.settlement.CumulationFeature;
import cdm.product.common.settlement.CumulationFeature.CumulationFeatureBuilder;
import cdm.product.common.settlement.CumulationFeature.CumulationFeatureBuilderImpl;
import cdm.product.common.settlement.CumulationFeature.CumulationFeatureImpl;
import cdm.product.common.settlement.meta.CumulationFeatureMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * Describe provisions which define how the quantity is incremented over time. When cumulationDates only is defined, the cumulation for such particular period is &#39;guarenteed&#39;. In case the cumulation would be &#39;contigent&#39;, Knock-In conditions may be defined. Besides, a &#39;lever&#39; may also be defined i.e. multiplier value to apply on the quantity incremented. Main business case is to define &#39;Accumulator&#39; or &#39;Decumulator&#39; products, which final payoff calculation eventually consists in applying the payout formula to an aggregated quantity, that being a sum of multiple quantity increment periods - hence the multiple cardinality of this attribute. For intance, say A + B + C + ... + N-1 are distinct cumulation conditions in termsheet, then such payoff would be represented by defining N distinct CumulationFeature in array e.g. CumulationFeature[A], CumulationFeature[B], CumulationFeature[C], [...], CumulationFeature[N-1], each with any appropriate &#39;guaranteed&#39; or &#39;contigent&#39; and/or &#39;levered&#39; features, as need be.
 * @version ${project.version}
 */
@RosettaDataType(value="CumulationFeature", builder=CumulationFeature.CumulationFeatureBuilderImpl.class, version="${project.version}")
public interface CumulationFeature extends RosettaModelObject {

	CumulationFeatureMeta metaData = new CumulationFeatureMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	CumulationFeature build();
	
	CumulationFeature.CumulationFeatureBuilder toBuilder();
	
	static CumulationFeature.CumulationFeatureBuilder builder() {
		return new CumulationFeature.CumulationFeatureBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CumulationFeature> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CumulationFeature> getType() {
		return CumulationFeature.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface CumulationFeatureBuilder extends CumulationFeature, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		CumulationFeature.CumulationFeatureBuilder prune();
	}

	/*********************** Immutable Implementation of CumulationFeature  ***********************/
	class CumulationFeatureImpl implements CumulationFeature {
		
		protected CumulationFeatureImpl(CumulationFeature.CumulationFeatureBuilder builder) {
		}
		
		@Override
		public CumulationFeature build() {
			return this;
		}
		
		@Override
		public CumulationFeature.CumulationFeatureBuilder toBuilder() {
			CumulationFeature.CumulationFeatureBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CumulationFeature.CumulationFeatureBuilder builder) {
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			return _result;
		}
		
		@Override
		public String toString() {
			return "CumulationFeature {" +
			'}';
		}
	}

	/*********************** Builder Implementation of CumulationFeature  ***********************/
	class CumulationFeatureBuilderImpl implements CumulationFeature.CumulationFeatureBuilder {
	
	
		public CumulationFeatureBuilderImpl() {
		}
	
	
		
		@Override
		public CumulationFeature build() {
			return new CumulationFeature.CumulationFeatureImpl(this);
		}
		
		@Override
		public CumulationFeature.CumulationFeatureBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CumulationFeature.CumulationFeatureBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CumulationFeature.CumulationFeatureBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CumulationFeature.CumulationFeatureBuilder o = (CumulationFeature.CumulationFeatureBuilder) other;
			
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			return _result;
		}
		
		@Override
		public String toString() {
			return "CumulationFeatureBuilder {" +
			'}';
		}
	}
}
