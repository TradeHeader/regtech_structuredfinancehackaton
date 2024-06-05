package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.MasterConfirmationBase;
import cdm.legaldocumentation.master.MasterConfirmationBase.MasterConfirmationBaseBuilder;
import cdm.legaldocumentation.master.MasterConfirmationBase.MasterConfirmationBaseBuilderImpl;
import cdm.legaldocumentation.master.MasterConfirmationBase.MasterConfirmationBaseImpl;
import cdm.legaldocumentation.master.meta.MasterConfirmationBaseMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * Legal agreement specification for General Terms and Elections that are applicable across multiple confirmations and are referenced by these confirmations.
 * @version ${project.version}
 */
@RosettaDataType(value="MasterConfirmationBase", builder=MasterConfirmationBase.MasterConfirmationBaseBuilderImpl.class, version="${project.version}")
public interface MasterConfirmationBase extends RosettaModelObject {

	MasterConfirmationBaseMeta metaData = new MasterConfirmationBaseMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	MasterConfirmationBase build();
	
	MasterConfirmationBase.MasterConfirmationBaseBuilder toBuilder();
	
	static MasterConfirmationBase.MasterConfirmationBaseBuilder builder() {
		return new MasterConfirmationBase.MasterConfirmationBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MasterConfirmationBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MasterConfirmationBase> getType() {
		return MasterConfirmationBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface MasterConfirmationBaseBuilder extends MasterConfirmationBase, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		MasterConfirmationBase.MasterConfirmationBaseBuilder prune();
	}

	/*********************** Immutable Implementation of MasterConfirmationBase  ***********************/
	class MasterConfirmationBaseImpl implements MasterConfirmationBase {
		
		protected MasterConfirmationBaseImpl(MasterConfirmationBase.MasterConfirmationBaseBuilder builder) {
		}
		
		@Override
		public MasterConfirmationBase build() {
			return this;
		}
		
		@Override
		public MasterConfirmationBase.MasterConfirmationBaseBuilder toBuilder() {
			MasterConfirmationBase.MasterConfirmationBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MasterConfirmationBase.MasterConfirmationBaseBuilder builder) {
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
			return "MasterConfirmationBase {" +
			'}';
		}
	}

	/*********************** Builder Implementation of MasterConfirmationBase  ***********************/
	class MasterConfirmationBaseBuilderImpl implements MasterConfirmationBase.MasterConfirmationBaseBuilder {
	
	
		public MasterConfirmationBaseBuilderImpl() {
		}
	
	
		
		@Override
		public MasterConfirmationBase build() {
			return new MasterConfirmationBase.MasterConfirmationBaseImpl(this);
		}
		
		@Override
		public MasterConfirmationBase.MasterConfirmationBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterConfirmationBase.MasterConfirmationBaseBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MasterConfirmationBase.MasterConfirmationBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MasterConfirmationBase.MasterConfirmationBaseBuilder o = (MasterConfirmationBase.MasterConfirmationBaseBuilder) other;
			
			
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
			return "MasterConfirmationBaseBuilder {" +
			'}';
		}
	}
}
