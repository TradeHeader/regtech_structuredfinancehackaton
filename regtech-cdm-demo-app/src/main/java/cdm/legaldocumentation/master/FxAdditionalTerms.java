package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.FxAdditionalTerms;
import cdm.legaldocumentation.master.FxAdditionalTerms.FxAdditionalTermsBuilder;
import cdm.legaldocumentation.master.FxAdditionalTerms.FxAdditionalTermsBuilderImpl;
import cdm.legaldocumentation.master.FxAdditionalTerms.FxAdditionalTermsImpl;
import cdm.legaldocumentation.master.meta.FxAdditionalTermsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * TransactionAdditionalTerms which apply to the CurrencyPair asset class.
 * @version ${project.version}
 */
@RosettaDataType(value="FxAdditionalTerms", builder=FxAdditionalTerms.FxAdditionalTermsBuilderImpl.class, version="${project.version}")
public interface FxAdditionalTerms extends RosettaModelObject {

	FxAdditionalTermsMeta metaData = new FxAdditionalTermsMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	FxAdditionalTerms build();
	
	FxAdditionalTerms.FxAdditionalTermsBuilder toBuilder();
	
	static FxAdditionalTerms.FxAdditionalTermsBuilder builder() {
		return new FxAdditionalTerms.FxAdditionalTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FxAdditionalTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FxAdditionalTerms> getType() {
		return FxAdditionalTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface FxAdditionalTermsBuilder extends FxAdditionalTerms, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		FxAdditionalTerms.FxAdditionalTermsBuilder prune();
	}

	/*********************** Immutable Implementation of FxAdditionalTerms  ***********************/
	class FxAdditionalTermsImpl implements FxAdditionalTerms {
		
		protected FxAdditionalTermsImpl(FxAdditionalTerms.FxAdditionalTermsBuilder builder) {
		}
		
		@Override
		public FxAdditionalTerms build() {
			return this;
		}
		
		@Override
		public FxAdditionalTerms.FxAdditionalTermsBuilder toBuilder() {
			FxAdditionalTerms.FxAdditionalTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FxAdditionalTerms.FxAdditionalTermsBuilder builder) {
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
			return "FxAdditionalTerms {" +
			'}';
		}
	}

	/*********************** Builder Implementation of FxAdditionalTerms  ***********************/
	class FxAdditionalTermsBuilderImpl implements FxAdditionalTerms.FxAdditionalTermsBuilder {
	
	
		public FxAdditionalTermsBuilderImpl() {
		}
	
	
		
		@Override
		public FxAdditionalTerms build() {
			return new FxAdditionalTerms.FxAdditionalTermsImpl(this);
		}
		
		@Override
		public FxAdditionalTerms.FxAdditionalTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxAdditionalTerms.FxAdditionalTermsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FxAdditionalTerms.FxAdditionalTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FxAdditionalTerms.FxAdditionalTermsBuilder o = (FxAdditionalTerms.FxAdditionalTermsBuilder) other;
			
			
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
			return "FxAdditionalTermsBuilder {" +
			'}';
		}
	}
}
