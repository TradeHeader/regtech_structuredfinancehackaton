package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.Representations;
import cdm.legaldocumentation.master.Representations.RepresentationsBuilder;
import cdm.legaldocumentation.master.Representations.RepresentationsBuilderImpl;
import cdm.legaldocumentation.master.Representations.RepresentationsImpl;
import cdm.legaldocumentation.master.meta.RepresentationsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * @version ${project.version}
 */
@RosettaDataType(value="Representations", builder=Representations.RepresentationsBuilderImpl.class, version="${project.version}")
public interface Representations extends RosettaModelObject {

	RepresentationsMeta metaData = new RepresentationsMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	Representations build();
	
	Representations.RepresentationsBuilder toBuilder();
	
	static Representations.RepresentationsBuilder builder() {
		return new Representations.RepresentationsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Representations> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Representations> getType() {
		return Representations.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
	}
	

	/*********************** Builder Interface  ***********************/
	interface RepresentationsBuilder extends Representations, RosettaModelObjectBuilder {

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
		}
		

		Representations.RepresentationsBuilder prune();
	}

	/*********************** Immutable Implementation of Representations  ***********************/
	class RepresentationsImpl implements Representations {
		
		protected RepresentationsImpl(Representations.RepresentationsBuilder builder) {
		}
		
		@Override
		public Representations build() {
			return this;
		}
		
		@Override
		public Representations.RepresentationsBuilder toBuilder() {
			Representations.RepresentationsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Representations.RepresentationsBuilder builder) {
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
			return "Representations {" +
			'}';
		}
	}

	/*********************** Builder Implementation of Representations  ***********************/
	class RepresentationsBuilderImpl implements Representations.RepresentationsBuilder {
	
	
		public RepresentationsBuilderImpl() {
		}
	
	
		
		@Override
		public Representations build() {
			return new Representations.RepresentationsImpl(this);
		}
		
		@Override
		public Representations.RepresentationsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Representations.RepresentationsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Representations.RepresentationsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Representations.RepresentationsBuilder o = (Representations.RepresentationsBuilder) other;
			
			
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
			return "RepresentationsBuilder {" +
			'}';
		}
	}
}
