package cdm.legaldocumentation.csa;

import cdm.legaldocumentation.csa.CollateralTransferAgreementElections;
import cdm.legaldocumentation.csa.CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder;
import cdm.legaldocumentation.csa.CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilderImpl;
import cdm.legaldocumentation.csa.CollateralTransferAgreementElections.CollateralTransferAgreementElectionsImpl;
import cdm.legaldocumentation.csa.meta.CollateralTransferAgreementElectionsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * The set of elections which specify a Collateral Transfer Agreement
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralTransferAgreementElections", builder=CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilderImpl.class, version="${project.version}")
public interface CollateralTransferAgreementElections extends RosettaModelObject {

	CollateralTransferAgreementElectionsMeta metaData = new CollateralTransferAgreementElectionsMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	CollateralTransferAgreementElections build();
	
	CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder toBuilder();
	
	static CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder builder() {
		return new CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralTransferAgreementElections> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralTransferAgreementElections> getType() {
		return CollateralTransferAgreementElections.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralTransferAgreementElectionsBuilder extends CollateralTransferAgreementElections, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralTransferAgreementElections  ***********************/
	class CollateralTransferAgreementElectionsImpl implements CollateralTransferAgreementElections {
		
		protected CollateralTransferAgreementElectionsImpl(CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder builder) {
		}
		
		@Override
		public CollateralTransferAgreementElections build() {
			return this;
		}
		
		@Override
		public CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder toBuilder() {
			CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder builder) {
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
			return "CollateralTransferAgreementElections {" +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralTransferAgreementElections  ***********************/
	class CollateralTransferAgreementElectionsBuilderImpl implements CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder {
	
	
		public CollateralTransferAgreementElectionsBuilderImpl() {
		}
	
	
		
		@Override
		public CollateralTransferAgreementElections build() {
			return new CollateralTransferAgreementElections.CollateralTransferAgreementElectionsImpl(this);
		}
		
		@Override
		public CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder o = (CollateralTransferAgreementElections.CollateralTransferAgreementElectionsBuilder) other;
			
			
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
			return "CollateralTransferAgreementElectionsBuilder {" +
			'}';
		}
	}
}
