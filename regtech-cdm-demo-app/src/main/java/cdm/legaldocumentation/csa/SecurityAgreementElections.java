package cdm.legaldocumentation.csa;

import cdm.legaldocumentation.csa.SecurityAgreementElections;
import cdm.legaldocumentation.csa.SecurityAgreementElections.SecurityAgreementElectionsBuilder;
import cdm.legaldocumentation.csa.SecurityAgreementElections.SecurityAgreementElectionsBuilderImpl;
import cdm.legaldocumentation.csa.SecurityAgreementElections.SecurityAgreementElectionsImpl;
import cdm.legaldocumentation.csa.meta.SecurityAgreementElectionsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * The set of elections which specify a Security Agremeent
 * @version ${project.version}
 */
@RosettaDataType(value="SecurityAgreementElections", builder=SecurityAgreementElections.SecurityAgreementElectionsBuilderImpl.class, version="${project.version}")
public interface SecurityAgreementElections extends RosettaModelObject {

	SecurityAgreementElectionsMeta metaData = new SecurityAgreementElectionsMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	SecurityAgreementElections build();
	
	SecurityAgreementElections.SecurityAgreementElectionsBuilder toBuilder();
	
	static SecurityAgreementElections.SecurityAgreementElectionsBuilder builder() {
		return new SecurityAgreementElections.SecurityAgreementElectionsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SecurityAgreementElections> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SecurityAgreementElections> getType() {
		return SecurityAgreementElections.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface SecurityAgreementElectionsBuilder extends SecurityAgreementElections, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		SecurityAgreementElections.SecurityAgreementElectionsBuilder prune();
	}

	/*********************** Immutable Implementation of SecurityAgreementElections  ***********************/
	class SecurityAgreementElectionsImpl implements SecurityAgreementElections {
		
		protected SecurityAgreementElectionsImpl(SecurityAgreementElections.SecurityAgreementElectionsBuilder builder) {
		}
		
		@Override
		public SecurityAgreementElections build() {
			return this;
		}
		
		@Override
		public SecurityAgreementElections.SecurityAgreementElectionsBuilder toBuilder() {
			SecurityAgreementElections.SecurityAgreementElectionsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SecurityAgreementElections.SecurityAgreementElectionsBuilder builder) {
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
			return "SecurityAgreementElections {" +
			'}';
		}
	}

	/*********************** Builder Implementation of SecurityAgreementElections  ***********************/
	class SecurityAgreementElectionsBuilderImpl implements SecurityAgreementElections.SecurityAgreementElectionsBuilder {
	
	
		public SecurityAgreementElectionsBuilderImpl() {
		}
	
	
		
		@Override
		public SecurityAgreementElections build() {
			return new SecurityAgreementElections.SecurityAgreementElectionsImpl(this);
		}
		
		@Override
		public SecurityAgreementElections.SecurityAgreementElectionsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityAgreementElections.SecurityAgreementElectionsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityAgreementElections.SecurityAgreementElectionsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SecurityAgreementElections.SecurityAgreementElectionsBuilder o = (SecurityAgreementElections.SecurityAgreementElectionsBuilder) other;
			
			
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
			return "SecurityAgreementElectionsBuilder {" +
			'}';
		}
	}
}
