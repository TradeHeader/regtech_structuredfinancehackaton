package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.DeterminationRolesAndTerms;
import cdm.legaldocumentation.master.DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder;
import cdm.legaldocumentation.master.DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilderImpl;
import cdm.legaldocumentation.master.DeterminationRolesAndTerms.DeterminationRolesAndTermsImpl;
import cdm.legaldocumentation.master.meta.DeterminationRolesAndTermsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * Defines the roles and related terms which document the agreement of parties about any determination requirements ; mostly about Extraordinary Events, without being necessarily restricted to such scope, as further specified in the particular product at stake e.g. for instance when Calculation Agent is mentioned as the Price Determination Method enumarated value, etc.
 * @version ${project.version}
 */
@RosettaDataType(value="DeterminationRolesAndTerms", builder=DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilderImpl.class, version="${project.version}")
public interface DeterminationRolesAndTerms extends RosettaModelObject {

	DeterminationRolesAndTermsMeta metaData = new DeterminationRolesAndTermsMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	DeterminationRolesAndTerms build();
	
	DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder toBuilder();
	
	static DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder builder() {
		return new DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DeterminationRolesAndTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DeterminationRolesAndTerms> getType() {
		return DeterminationRolesAndTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface DeterminationRolesAndTermsBuilder extends DeterminationRolesAndTerms, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder prune();
	}

	/*********************** Immutable Implementation of DeterminationRolesAndTerms  ***********************/
	class DeterminationRolesAndTermsImpl implements DeterminationRolesAndTerms {
		
		protected DeterminationRolesAndTermsImpl(DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder builder) {
		}
		
		@Override
		public DeterminationRolesAndTerms build() {
			return this;
		}
		
		@Override
		public DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder toBuilder() {
			DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder builder) {
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
			return "DeterminationRolesAndTerms {" +
			'}';
		}
	}

	/*********************** Builder Implementation of DeterminationRolesAndTerms  ***********************/
	class DeterminationRolesAndTermsBuilderImpl implements DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder {
	
	
		public DeterminationRolesAndTermsBuilderImpl() {
		}
	
	
		
		@Override
		public DeterminationRolesAndTerms build() {
			return new DeterminationRolesAndTerms.DeterminationRolesAndTermsImpl(this);
		}
		
		@Override
		public DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder o = (DeterminationRolesAndTerms.DeterminationRolesAndTermsBuilder) other;
			
			
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
			return "DeterminationRolesAndTermsBuilder {" +
			'}';
		}
	}
}
