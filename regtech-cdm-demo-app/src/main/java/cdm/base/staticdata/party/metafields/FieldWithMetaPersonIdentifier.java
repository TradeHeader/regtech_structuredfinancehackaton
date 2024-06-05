package cdm.base.staticdata.party.metafields;

import cdm.base.staticdata.party.PersonIdentifier;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.BasicRosettaMetaData;
import com.rosetta.model.lib.meta.FieldWithMeta;
import com.rosetta.model.lib.meta.FieldWithMeta.FieldWithMetaBuilder;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version 1
 */
@RosettaDataType(value="FieldWithMetaPersonIdentifier", builder=FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaPersonIdentifier extends RosettaModelObject, FieldWithMeta<PersonIdentifier>, GlobalKey {

	FieldWithMetaPersonIdentifierMeta metaData = new FieldWithMetaPersonIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	PersonIdentifier getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaPersonIdentifier build();
	
	FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder toBuilder();
	
	static FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder builder() {
		return new FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaPersonIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaPersonIdentifier> getType() {
		return FieldWithMetaPersonIdentifier.class;
	}
	
	@Override
	default Class<PersonIdentifier> getValueType() {
		return PersonIdentifier.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, PersonIdentifier.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaPersonIdentifierBuilder extends FieldWithMetaPersonIdentifier, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<PersonIdentifier> {
		PersonIdentifier.PersonIdentifierBuilder getOrCreateValue();
		PersonIdentifier.PersonIdentifierBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder setValue(PersonIdentifier value);
		FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, PersonIdentifier.PersonIdentifierBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaPersonIdentifier  ***********************/
	class FieldWithMetaPersonIdentifierImpl implements FieldWithMetaPersonIdentifier {
		private final PersonIdentifier value;
		private final MetaFields meta;
		
		protected FieldWithMetaPersonIdentifierImpl(FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public PersonIdentifier getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaPersonIdentifier build() {
			return this;
		}
		
		@Override
		public FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder toBuilder() {
			FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaPersonIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaPersonIdentifier {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaPersonIdentifier  ***********************/
	class FieldWithMetaPersonIdentifierBuilderImpl implements FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder {
	
		protected PersonIdentifier.PersonIdentifierBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaPersonIdentifierBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public PersonIdentifier.PersonIdentifierBuilder getValue() {
			return value;
		}
		
		@Override
		public PersonIdentifier.PersonIdentifierBuilder getOrCreateValue() {
			PersonIdentifier.PersonIdentifierBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = PersonIdentifier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("value")
		public FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder setValue(PersonIdentifier value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaPersonIdentifier build() {
			return new FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierImpl(this);
		}
		
		@Override
		public FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null && getValue().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder o = (FieldWithMetaPersonIdentifier.FieldWithMetaPersonIdentifierBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaPersonIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaPersonIdentifierBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaPersonIdentifierMeta extends BasicRosettaMetaData<FieldWithMetaPersonIdentifier>{

}
